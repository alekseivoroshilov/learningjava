import java.util.Map;
import java.util.HashMap;

public class MeasurementSystem {
    private String firstAmountName;
    private int firstAmount;
    private String secondAmountName;
    private int secondAmount;
    private Map<String, Integer> mapOfMeasurement = new HashMap<String, Integer>();
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
    public static void init() {
        MeasurementSystem metric = new MeasurementSystem();
        MeasurementSystem time = new MeasurementSystem();
        metric.mapOfMeasurement.put("km",1000);
        metric.mapOfMeasurement.put("m", 1000);
        metric.mapOfMeasurement.put("cm", 100);
        time.mapOfMeasurement.put("h",24);
        time.mapOfMeasurement.put("min", 60);
        time.mapOfMeasurement.put("sec", 60);
    }
    public Number newNumber(String string){
        Number number = Number.fromString(string);
        number.amount =
        return number;
    }

}
