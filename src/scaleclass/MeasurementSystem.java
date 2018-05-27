import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedHashMap;

class MeasurementSystem {
    LinkedHashMap<String, Double> mapOfMeasurement = new LinkedHashMap<String, Double>(); //Linked, потому что важен порядок
    void addDependence(String dimensionName, double amount) {
        LinkedHashMap<String, Double> pair = new LinkedHashMap<String, Double>();
        pair.put(dimensionName, amount);
        mapOfMeasurement.putAll(pair);
    }

    Number newNumber(String string) {
        ArrayList<String> form = new ArrayList<String>();
        Collections.addAll(form, string.split(" "));
        double amount = Double.parseDouble(form.get(0));
        String dimName = form.get(1);
        Number number = new Number(amount, dimName,this);
        transformToAverageDimension(number);
        return number;
    }

    Number recalculate(Number number) {
        Number recalculatedNumber = new Number(number.getAmount(), number.getdimensionName(), this);
        int j = 0;
        for (Map.Entry e : mapOfMeasurement.entrySet()) {
            if (e.getKey().equals(number.getdimensionName())) break;
            else j++;
        }
        //нас итересует то, если число больше, чем нужно, или наоборот
        if(recalculatedNumber.getAmount() == null) throw new IllegalArgumentException("число null");
        if (Math.abs(recalculatedNumber.getAmount()) >=
                Math.abs(mapOfMeasurement.get(recalculatedNumber.getdimensionName()))) {
            //проверим, нет ли 101 cm, которые можно перевести в 1.01 "m"
            // достаю ключ более высокой/низкой величины ("km" выше, чем "m")
            while (recalculatedNumber.getAmount() > mapOfMeasurement.get(recalculatedNumber.getdimensionName())) {
                if (j <= 0) break;
                String keyOfHigherDimension = getKeyByIndex(j);
                recalculatedNumber = new
                        Number(recalculatedNumber.getAmount() / mapOfMeasurement.get(keyOfHigherDimension),
                        getKeyByIndex(j - 1),this);
                j--;
                // если ключ величны более высокой, то идём влево по списку: ("km", 1.0,<--- "m", 1000.0)
                // иначе - вправо
            }

        } else if (Math.abs(number.getAmount()) < 1.0) {
            //проверим, нет ли 0.1 m, которые можно перевести в 10 "cm"
            // достаю ключ более высокой/низкой величины ("km" выше, чем "m")
            while (recalculatedNumber.getAmount() < 1.0) {
                j++;
                if (j >= mapOfMeasurement.size()) break;
                String keyOfLowerDimension = getKeyByIndex(j);
                recalculatedNumber = new
                        Number((Math.round(number.getAmount() *
                        mapOfMeasurement.get(keyOfLowerDimension) * 1000000.0)) / 1000000.0,
                        keyOfLowerDimension,this);

                // если ключ величны более высокой, то идём влево по списку: ("km", 1.0,---> "m", 1000.0)
            }
        }
        return recalculatedNumber;
    }

    private String getKeyByIndex(int index) {
        if (index < 0 || mapOfMeasurement.size() <= index) {
            throw new IndexOutOfBoundsException();
        }
        Map.Entry<String, Double> e = null;
        Iterator<Map.Entry<String, Double>> pair = mapOfMeasurement.entrySet().iterator();
        while (0 <= index-- && pair.hasNext()) { //натыкаясь на инкремент/декремент, число меняется
            e = pair.next();
        }
        return e.getKey(); // спросить
    }

    // этот метод делает то же, что и recalculate, но переводит и cm, и даже km в метры.
    Number transformToAverageDimension(Number number) {
        Number recalculatedNumber = new Number(number.getAmount(), number.getdimensionName(), this);
        if (mapOfMeasurement.containsKey(number.getdimensionName())) { //"13 m" : если в metric есть размерность "m" ...
            int j = 0;
            for (Map.Entry e : mapOfMeasurement.entrySet()) {
                if (e.getKey().equals(number.getdimensionName())) break;
                else j++;
            }
            Integer indexOfAverageDimension = mapOfMeasurement.size() / 2;
            while (j != indexOfAverageDimension) {
                if (j < indexOfAverageDimension) {
                    j++;
                    String keyOfLowerDimension = getKeyByIndex(j);
                    recalculatedNumber = new
                            Number((Math.round(number.getAmount() *
                            mapOfMeasurement.get(keyOfLowerDimension) * 1000000.0)) / 1000000.0,
                            keyOfLowerDimension,this);

                } else {
                    if (j <= 0) break;
                    String keyOfHigherDimension = getKeyByIndex(j);
                    recalculatedNumber = new
                            Number(recalculatedNumber.getAmount() / mapOfMeasurement.get(keyOfHigherDimension),
                            getKeyByIndex(j - 1),this);
                    j--;

                }
            }
        }
        return recalculatedNumber;
    }
}