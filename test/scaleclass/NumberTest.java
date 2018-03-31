import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class NumberTest {
    /*@BeforeAll void init(){
        MeasurementSystem measurementSystem = new MeasurementSystem();
        measurementSystem.init();
        System.out.println(measurementSystem.mapOfMeasurement);
    }*/
    @org.junit.jupiter.api.Test
    void sum() {
        MeasurementSystem ms = new MeasurementSystem();
        Number number = ms.newNumber("6 m");
        number.add(ms.newNumber("0.5 km"));
        assertTrue(number.compareTo(new Number(506.0,"m")));
        number = ms.newNumber("0.6 cm");
        number.add(ms.newNumber("0.5 cm"));
        System.out.println(number.getAmount() + " " + number.getdimensionName() + " result");
        assertTrue(number.compareTo(new Number(1.1,"cm")));
        /*actual = Number.sum(Number.fromString("2 m"), Number.fromString("15 cm"));
        assertEquals("2.15 m", Number.toString(actual));
        actual = Number.sum(Number.fromString("15 cm"), Number.fromString("15 cm"));
        assertEquals("30.0 cm", Number.toString(actual));
        actual = Number.sum(Number.fromString("0.0 cm"), Number.fromString("0 m"));
        assertEquals("0.0 cm", Number.toString(actual));*/
    }
    @org.junit.jupiter.api.Test
    void subtraction() {
        MeasurementSystem ms = new MeasurementSystem();
        Number number = ms.newNumber("2 m");
        number.subtraction(ms.newNumber("1 km"));
        System.out.println(number.getAmount() + " " + number.getdimensionName() + " subtraction");
        assertTrue(number.compareTo(new Number(-998.0,"m")));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        MeasurementSystem ms = new MeasurementSystem();
        Number number = ms.newNumber("2 m");
        number.multiplication(4000);
        assertEquals(ms.newNumber("8.0 km"), number);
    }

    @org.junit.jupiter.api.Test
    void divisionInto() {
        MeasurementSystem ms = new MeasurementSystem();
        Number number = ms.newNumber("2 m");
        number.divisionInto(4);
        assertEquals(ms.newNumber("50 cm"), number);
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        MeasurementSystem ms = new MeasurementSystem();
        Number number = ms.newNumber("505 m");
        assertTrue(number.compareTo(ms.newNumber("0.505 km")));
    }
}