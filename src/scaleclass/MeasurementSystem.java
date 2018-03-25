import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Collections;

public class MeasurementSystem {
    private Map<String, Double> mapOfMeasurement = new HashMap<String, Double>();
    private Map<String, Double> mapOfMeasurementNames = new HashMap<String, Double>();

    private ArrayList<MeasurementSystem> measurementList = new ArrayList<>();
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
        metric.mapOfMeasurement.put("km", 1000.0);
        metric.mapOfMeasurement.put("m", 1000.0);
        metric.mapOfMeasurement.put("cm", 100.0);
        metric.mapOfMeasurementNames.put("km", "m");
        time.mapOfMeasurement.put("h", 24.0);
        time.mapOfMeasurement.put("min", 60.0);
        time.mapOfMeasurement.put("sec", 60.0);
        measurementList.add(metric);
        measurementList.add(time);
    }

    public Number newNumber(String string) {
        Map<String, Double> measurement;
        Number number = Number.fromString(string);
        for (int i = 0; i < measurementList.size(); i++) {
            measurement = measurementList.get(i).mapOfMeasurement;
            if (measurement.containsKey(number.getdimensionName())) { //"13 m" : если в metric есть размерность "m" ...
                int j = 0;
                if (number.getAmount() >= measurement.get(number.getdimensionName())) { //проверим, нет ли 100 cm, которые можно перевести в 1 "m"\
                    for (Map.Entry e : measurement.entrySet()) {
                        if (e.getKey().equals(number.getdimensionName())) break;
                        j++;
                    /*for (int j = 0; j < measurement.size(); i++){
                        if(measurement.keySet().toArray(new Object[measurement.size()])[j].equals(number.getdimensionName())){
                            Object value = measurement.get(key);
                        }*/
                    }
                    // достаю ключ более высокой величины ("km" больше, чем "m")
                    String keyOfHigherDimension = getKeyByIndex(measurement, j);
                    number.setAmount(number.getAmount() / measurement.get(keyOfHigherDimension));

                }
            }
        }
        /*for (MeasurementSystem measurement : measurementList){ // то, что выше, более читабельно
            if(measurement.mapOfMeasurement.containsKey(number.getdimensionName())){

            }
        }*/

        return number;
    }

    private String getKeyByIndex(Map<String, Double> measurement, int index) {
        if (index < 0 || measurement.size() <= index) {
            throw new IndexOutOfBoundsException();
        }
        Map.Entry<String, Double> e = null;
        Iterator<Map.Entry<String, Double>> pair = measurement.entrySet().iterator();
        while (0 <= index-- && pair.hasNext()) {
            e = pair.next();
        }

        return e.getKey(); // спросить

    }
}