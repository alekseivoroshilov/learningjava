import static org.junit.jupiter.api.Assertions.*;

public class NumberTest {
    /*@BeforeAll void init(){   //спросить про ошибку (2)
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
        Number number2 = new Number(0.5, "km", metric);
        number1 = metric.transformToAverageDimension(number1);
        number2 = metric.transformToAverageDimension(number2);
        number1.add(number2);
        number1 = metric.recalculate(number1);
        assertTrue(number1.equals(new Number(506.0,"m", metric)));
    }
    @org.junit.jupiter.api.Test
    void subtraction() {
        MeasurementSystem metric = new MeasurementSystem();
        metric.addDependence("km", 1000.0);
        metric.addDependence("m", 1000.0);
        metric.addDependence("cm", 100.0);
        Number number1 = metric.newNumber("2 m");
        Number number2 = metric.newNumber("1 km");
        number1 = metric.transformToAverageDimension(number1);
        number2 = metric.transformToAverageDimension(number2);
        number1.subtraction(number2);
        assertEquals(number1, new Number(-998.0,"m", metric));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        MeasurementSystem ms = new MeasurementSystem();
        ms.addDependence("bitcoin", 1000.0);
        ms.addDependence("$", 6700.0);
        ms.addDependence("rub", 65.0);
        Number number = ms.newNumber("1.0 rub");
        number.multiplication(4030.0);
        number = ms.recalculate(number);
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
        number = ms.recalculate(number);
        assertEquals(ms.newNumber("12 h"), number);
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        MeasurementSystem metric = new MeasurementSystem();
        Number number1 = metric.newNumber("505 m");
        Number number2 = metric.newNumber("0.505 km");
        metric.addDependence("km", 1000.0);
        metric.addDependence("m", 1000.0);
        metric.addDependence("cm", 100.0);
        int result =  number1.compareTo(number2);
        assertEquals(0, result);

        number1 = metric.newNumber("505 m");
        number2 = metric.newNumber("505 cm");
        result =  number1.compareTo(number2);
        assertEquals(1, result);

        number1 = metric.newNumber("505 m");
        number2 = metric.newNumber("50505 cm");
        result =  number1.compareTo(number2);
        assertEquals(-1, result);

        number1 = metric.newNumber("0.505 km");
        number2 = metric.newNumber("50500 cm");
        result =  number1.compareTo(number2);
        assertEquals(0, result);

        number1 = metric.newNumber("6 m");
        number2 = metric.newNumber("10 km");
        result =  number1.compareTo(number2);
        assertEquals(-1, result);

        number1 = metric.newNumber("0 m");
        number2 = metric.newNumber("0 cm");
        result =  number1.compareTo(number2);
        assertEquals(0, result);
    }
}