import static org.junit.jupiter.api.Assertions.*;

class NumberTest {
    @org.junit.jupiter.api.Test
    void sum() {
        Number.init();
        Number actual = Operations.sum(Number.fromString("2 m"), Number.fromString("4 m"));
        assertEquals("6.0 m", Number.toString(actual));
        actual = Operations.sum(Number.fromString("2 m"), Number.fromString("15 cm"));
        assertEquals("2.15 m", Number.toString(actual));
        actual = Operations.sum(Number.fromString("15 cm"), Number.fromString("15 cm"));
        assertEquals("30.0 cm", Number.toString(actual));
    }
    @org.junit.jupiter.api.Test
    void subtraction() {
        Number.init();
        Number actual = Operations.subtraction(Number.fromString("2 m"), Number.fromString("4 m"));
        assertEquals("-2.0 m", Number.toString(actual));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        Number.init();
        Number actual = Number.fromString("2 m");
        Operations.multiplication(actual, 4);
        assertEquals("8.0 m", Number.toString(actual));
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        Number expected = Number.fromString("8 m");
        Number actual = new Number(8.0,0.0, "m");
        assertTrue(Operations.compareTo(expected, actual));
    }

    @org.junit.jupiter.api.Test
    void divisionInto() {
        Number.init();
        Number actual = Number.fromString("8 m");
        Operations.divisionInto(actual, 4);
        assertEquals("2.0 m", Number.toString(actual));
    }

}