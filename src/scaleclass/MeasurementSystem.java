import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Collections;

class MeasurementSystem {
    TreeMap<String, Double> mapOfMeasurement = new TreeMap<String, Double>(); //Дерево, потому что важен порядок
    ArrayList<MeasurementSystem> measurementList = new ArrayList<>();
    //private Map<String, Integer> distanceMetric = new HashMap<String, Integer>();
    //private Map<String, Integer> time = new HashMap<String, Integer>();

    /*public void addMeasurement(String amountName, String secondAmountName, int firstAmount, int secondAmount){
        this.firstAmountName = firstAmountName;
        this.secondAmountName = secondAmountName;
        this.firstAmount = firstAmount;
        this.secondAmount = secondAmount;
    }*/
    /*void addMeasurement(String amountName, int amount, String higherMeasurement){
        MeasurementSystem metric = new MeasurementSystem();

    }*/
    public void init() {
        MeasurementSystem metric = new MeasurementSystem(); //система мер определённых явлений как отдельный объект
        MeasurementSystem time = new MeasurementSystem();
        metric.mapOfMeasurement.put("km", 1000.0); //вот почему порядок важен, как я говорил выше
        metric.mapOfMeasurement.put("m", 1000.0);
        metric.mapOfMeasurement.put("cm", 100.0);
        time.mapOfMeasurement.put("h", 24.0);
        time.mapOfMeasurement.put("min", 60.0);
        time.mapOfMeasurement.put("sec", 60.0);
        measurementList.add(metric);
        measurementList.add(time);
    }

    public Number newNumber(String string) {
        ArrayList<String> form = new ArrayList<String>();
        Collections.addAll(form, string.split(" "));
        Number number = new Number();
        number.setAmount(Double.parseDouble(form.get(0)));
        number.setdimensionName(form.get(1));
        recalculate(number);
        /*Map<String, Double> measurement;

        for (int i = 0; i < measurementList.size(); i++) {
            measurement = measurementList.get(i).mapOfMeasurement;
            if (measurement.containsKey(number.getdimensionName())) { //"13 m" : если в metric есть размерность "m" ...
                int j = 0;
                for (Map.Entry e : measurement.entrySet()) {
                    if (e.getKey().equals(number.getdimensionName())) break;
                    j++;
                }
                //нас итересует то, если число больше, чем нужно, или наоборот
                if (number.getAmount() >= measurement.get(number.getdimensionName()) || (number.getAmount() < 1.0)){
                    //проверим, нет ли 100 cm, которые можно перевести в 1 "m" или наоборот
                    // достаю ключ более высокой/низкой величины ("km" выше, чем "m")
                    while(number.getAmount() > measurement.get(number.getdimensionName()) || number.getAmount() < 1.0){
                        String keyOfHigherDimension = getKeyByIndex(measurement, j);
                        number.setAmount(number.getAmount() / measurement.get(keyOfHigherDimension));
                        number.setdimensionName(keyOfHigherDimension);
                        if (number.getAmount() >= measurement.get(number.getdimensionName())) j--; else j++;
                        if (j <= 0 || j >= measurement.size()) return number; // если выхожу за пределы значений >km
                        // если ключ величны более высокой, то идём влево по списку: ("km", 1000.0,<--- "m", 1000.0)
                        // иначе - вправо
                    }

                return number;
                }
            }
        }*/
        return number;
    }
    public void recalculate(Number number) {
        Map<String, Double> measurement;
        for (int i = 0; i < measurementList.size(); i++) {
            measurement = measurementList.get(i).mapOfMeasurement;
            if (measurement.containsKey(number.getdimensionName())) { //"13 m" : если в metric есть размерность "m" ...
                int j = 0;
                for (Map.Entry e : measurement.entrySet()) {
                    if (e.getKey().equals(number.getdimensionName())) break;
                    else j++;
                }
                System.out.println(j + "recalculate j");
                //нас итересует то, если число больше, чем нужно, или наоборот
                if (number.getAmount() >= measurement.get(number.getdimensionName())) {
                    //проверим, нет ли 101 cm, которые можно перевести в 1.01 "m"
                    // достаю ключ более высокой/низкой величины ("km" выше, чем "m")

                    while (number.getAmount() > measurement.get(number.getdimensionName())) {
                        j--;
                        if (j <= 0) break;
                        String keyOfHigherDimension = getKeyByIndex(measurement, j);
                        System.out.println(keyOfHigherDimension);
                        number.setAmount(number.getAmount() / measurement.get(keyOfHigherDimension));
                        number.setdimensionName(keyOfHigherDimension);
                        System.out.println(number.getAmount() + " " + number.getdimensionName() + " recalculate method");
                        // если выхожу за пределы значений >km
                        // если ключ величны более высокой, то идём влево по списку: ("km", 1.0,<--- "m", 1000.0)
                        // иначе - вправо
                    }


                    //return number;
                } else if (number.getAmount() < 1.0) {
                    //проверим, нет ли 0.1 m, которые можно перевести в 10 "cm"
                    // достаю ключ более высокой/низкой величины ("km" выше, чем "m")

                    while (number.getAmount() < 1.0) {
                        j++;
                        if (j >= measurement.size()) break;
                        String keyOfHigherDimension = getKeyByIndex(measurement, j);
                        System.out.println(keyOfHigherDimension);
                        number.setAmount(number.getAmount() * measurement.get(keyOfHigherDimension));
                        number.setdimensionName(keyOfHigherDimension);
                        System.out.println(number.getAmount() + " " + number.getdimensionName() + " recalculate method");
                        // если выхожу за пределы значений <cm
                        // если ключ величны более высокой, то идём влево по списку: ("km", 1.0,---> "m", 1000.0)
                        // иначе - вправо
                    }
                    break;
                }
            }
            //return number;
        }
    }
    private String getKeyByIndex(Map<String, Double> measurement, int index) {
        if (index < 0 || measurement.size() <= index) {
            throw new IndexOutOfBoundsException();
        }
        Map.Entry<String, Double> e = null;
        Iterator<Map.Entry<String, Double>> pair = measurement.entrySet().iterator();
        while (0 <= index-- && pair.hasNext()) { //натыкаясь на инкремент/декремент, число меняется
            e = pair.next();
            System.out.println(e);
        }

        return e.getKey(); // спросить


    }
}