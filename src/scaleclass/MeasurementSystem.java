import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedHashMap;

class MeasurementSystem {
    LinkedHashMap<String, Double> mapOfMeasurement = new LinkedHashMap<String, Double>(); //Linked, потому что важен порядок
    //ArrayList<MeasurementSystem> measurementList = new ArrayList<>();


    /*public void init() {
        MeasurementSystem metric = new MeasurementSystem(); //система мер определённых явлений как отдельный объект
        MeasurementSystem time = new MeasurementSystem();
        metric.mapOfMeasurement.put("km", 1000); //вот почему порядок важен, как я говорил выше
        metric.mapOfMeasurement.put("m", 1000);
        metric.mapOfMeasurement.put("cm", 100);
        time.mapOfMeasurement.put("h", 24);
        time.mapOfMeasurement.put("min", 60);
        time.mapOfMeasurement.put("sec", 60);
        measurementList.add(metric);
        measurementList.add(time);
    }*/

    void addDependence(String dimensionName, double amount) {
        LinkedHashMap<String, Double> pair = new LinkedHashMap<String, Double>();
        pair.put(dimensionName, amount);
        mapOfMeasurement.putAll(pair);
    }

    Number newNumber(String string) {
        ArrayList<String> form = new ArrayList<String>();
        Collections.addAll(form, string.split(" "));
        Number number = new Number();
        number.setAmount(Double.parseDouble(form.get(0)));
        number.setdimensionName(form.get(1));
        transformToAverageDimension(number);
        return number;
    }

    void recalculate(Number number) {
        int j = 0;
        for (Map.Entry e : mapOfMeasurement.entrySet()) {
            if (e.getKey().equals(number.getdimensionName())) break;
            else j++;
        }
        //нас итересует то, если число больше, чем нужно, или наоборот
        if(number.getAmount() == null) throw new IllegalArgumentException("число null");
        if (Math.abs(number.getAmount()) >= Math.abs(mapOfMeasurement.get(number.getdimensionName()))) {
            //проверим, нет ли 101 cm, которые можно перевести в 1.01 "m"
            // достаю ключ более высокой/низкой величины ("km" выше, чем "m")
            while (number.getAmount() > mapOfMeasurement.get(number.getdimensionName())) {
                if (j <= 0) break;
                String keyOfHigherDimension = getKeyByIndex(j);
                number.setAmount(number.getAmount() / mapOfMeasurement.get(keyOfHigherDimension));
                keyOfHigherDimension = getKeyByIndex(j - 1);
                number.setdimensionName(keyOfHigherDimension);
                j--;
                // если выхожу за пределы значений >km
                // если ключ величны более высокой, то идём влево по списку: ("km", 1.0,<--- "m", 1000.0)
                // иначе - вправо
            }

        } else if (Math.abs(number.getAmount()) < 1.0) {
            //проверим, нет ли 0.1 m, которые можно перевести в 10 "cm"
            // достаю ключ более высокой/низкой величины ("km" выше, чем "m")
            while (number.getAmount() < 1.0) {
                j++;
                if (j >= mapOfMeasurement.size()) break;
                String keyOfLowerDimension = getKeyByIndex(j);
                //после того, что ниже, начинаешь верить в магию.
                number.setAmount((Math.round(number.getAmount() * mapOfMeasurement.get(keyOfLowerDimension) * 1000000.0)) / 1000000.0);
                number.setdimensionName(keyOfLowerDimension);
                // если выхожу за пределы значений <cm
                // если ключ величны более высокой, то идём влево по списку: ("km", 1.0,---> "m", 1000.0)
                // иначе - вправо
            }
        }
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
    void transformToAverageDimension(Number number) {
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
                    if (j >= mapOfMeasurement.size()) break;
                    String keyOfLowerDimension = getKeyByIndex(j);
                    number.setAmount((Math.round(number.getAmount() * mapOfMeasurement.get(keyOfLowerDimension) * 100000.0)) / 100000.0);
                    number.setdimensionName(keyOfLowerDimension);
                } else {
                    if (j <= 0) break;
                    String keyOfHigherDimension = getKeyByIndex(j);
                    number.setAmount(number.getAmount() / mapOfMeasurement.get(keyOfHigherDimension));
                    keyOfHigherDimension = getKeyByIndex(j - 1);
                    number.setdimensionName(keyOfHigherDimension);
                    j--;
                }
            }
        }
    }
}