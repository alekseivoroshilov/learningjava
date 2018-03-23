import static org.junit.jupiter.api.Assertions.*;

class NumberTest {
    @org.junit.jupiter.api.Test
    void sum() {
        Number.init();
        Number res = new Number(6,0,"m");
        assertTrue(res.equals( (new Number(2,0,"m")).add(new Number(4,0,"m"))));
        /*actual = Number.sum(Number.fromString("2 m"), Number.fromString("15 cm"));
        assertEquals("2.15 m", Number.toString(actual));
        actual = Number.sum(Number.fromString("15 cm"), Number.fromString("15 cm"));
        assertEquals("30.0 cm", Number.toString(actual));
        actual = Number.sum(Number.fromString("0.0 cm"), Number.fromString("0 m"));
        assertEquals("0.0 cm", Number.toString(actual));*/
    }
    @org.junit.jupiter.api.Test
    void subtraction() {
        Number.init();
        Number actual = Number.subtraction(Number.fromString("2 m"), Number.fromString("4 m"));
        assertEquals("-2.0 m", Number.toString(actual));
        actual = Number.subtraction(Number.fromString("2 h"), Number.fromString("1.5 h"));
        assertEquals("30.0 min", Number.toString(actual));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        Number.init();
        Number actual = Number.fromString("2 m");
        Number.multiplication(actual, 4);
        assertEquals("8.0 m", Number.toString(actual));
        actual = Number.fromString("15 cm");
        Number.multiplication(actual, 7);
        assertEquals("1.05 m", Number.toString(actual));
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        Number expected = Number.fromString("8 m");
        Number actual = new Number(8.0,0.0, "m");
        assertTrue(Number.compareTo(expected, actual));
    }

    @org.junit.jupiter.api.Test
    void divisionInto() {
        Number.init();
        Number actual = Number.fromString("8 m");
        Number.divisionInto(actual, 4);
        assertEquals("2.0 m", Number.toString(actual));
    }

}