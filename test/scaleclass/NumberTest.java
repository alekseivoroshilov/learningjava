import static org.junit.jupiter.api.Assertions.*;

class NumberTest {
    @org.junit.jupiter.api.Test
    void sum() {
        Number actual = Operations.sum(Number.fromString("2 m"), Number.fromString("4 m"));
        assertEquals("6.0 m", Number.toString(actual));
    }
    @org.junit.jupiter.api.Test
    void subtraction() {
        Number actual = Operations.subtraction(Number.fromString("2 m"), Number.fromString("4 m"));
        assertEquals("-2.0 m", Number.toString(actual));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        Number actual = Operations.multiplication(Number.fromString("2 m"), 4);
        assertEquals("8.0 m", Number.toString(actual));
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        Number expected = Number.fromString("8 m");
        Number actual = new Number(8.0, "m");
        assertTrue(Operations.compareTo(expected, actual));
    }

    @org.junit.jupiter.api.Test
    void divisionInto() {
        Number actual = Operations.divisionInto(Number.fromString("8 m"), 4);
        assertEquals("2.0 m", Number.toString(actual));
    }

}