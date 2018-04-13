import static org.junit.jupiter.api.Assertions.*;

public class NumberTest {
    /*@BeforeAll void init(){
        MeasurementSystem measurementSystem = new MeasurementSystem();
        measurementSystem.init();
        System.out.println(measurementSystem.mapOfMeasurement);
    }*/
    @org.junit.jupiter.api.Test
    void sum() {
        MeasurementSystem metric = new MeasurementSystem();
        metric.addDependence("km", 1000.0);
        metric.addDependence("m", 1000.0);
        metric.addDependence("cm", 100.0);
        Number number1 = metric.newNumber("6.0 m");
        Number number2 = metric.newNumber("0.5 km");
        //metric.transformToAverageDimension(number1);
        //metric.transformToAverageDimension(number2);
        number1.add(number2);
        metric.recalculate(number1);
        assertTrue(number1.compareTo(new Number(506.0,"m"), metric));
        //number = metric.newNumber("0.6 cm");
        //number.add(metric.newNumber("0.5 cm"));
        //System.out.println(number.getAmount() + " " + number.getdimensionName() + " result");
        //assertTrue(number.compareTo(new Number(1.1,"cm")));
    }
    @org.junit.jupiter.api.Test
    void subtraction() {
        MeasurementSystem metric = new MeasurementSystem();
        metric.addDependence("km", 1000.0);
        metric.addDependence("m", 1000.0);
        metric.addDependence("cm", 100.0);
        Number number1 = metric.newNumber("6.0 m");
        Number number2 = metric.newNumber("0.5 km");
        Number number = metric.newNumber("2 m");
        number.subtraction(metric.newNumber("1 km"));
        System.out.println(number.getAmount() + " " + number.getdimensionName() + " subtraction");
        assertTrue(number.compareTo(new Number(-998.0,"m"), metric));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        MeasurementSystem ms = new MeasurementSystem();
        ms.addDependence("bitcoin", 1000.0);
        ms.addDependence("$", 6700.0);
        ms.addDependence("rub", 65.0);
        Number number = ms.newNumber("1.0 rub");
        number.multiplication(4030);
        ms.recalculate(number);
        System.out.println(number.getAmount() + " " + number.getdimensionName());
        assertEquals(ms.newNumber("62 $"), number);
    }

    @org.junit.jupiter.api.Test
    void divisionInto() {
        MeasurementSystem ms = new MeasurementSystem();
        ms.addDependence("days", 365.0);
        ms.addDependence("h", 24.0);
        ms.addDependence("min", 60.0);
        Number number = ms.newNumber("1 days");
        number.divisionInto(2);
        ms.recalculate(number);
        assertEquals(ms.newNumber("12 h"), number);
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        MeasurementSystem metric = new MeasurementSystem();
        Number number2 = metric.newNumber("0.505 km");
        metric.addDependence("km", 1000.0);
        metric.addDependence("m", 1000.0);
        metric.addDependence("cm", 100.0);
        //metric.recalculate(number2);
        Number number = metric.newNumber("505 m");
        assertTrue(number.compareTo(number2, metric));
    }
}