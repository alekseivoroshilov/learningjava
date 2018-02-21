import static org.junit.jupiter.api.Assertions.*;

class NumberTest {
    @org.junit.jupiter.api.Test
    void sum() {
        //Number theFirst = new Operations().fromString("2 m");
        Number actual = new Operations().sum(new Operations().fromString("2 m"), new Operations().fromString("4 m"));
        assertEquals("6 m", actual);
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
    }

    @org.junit.jupiter.api.Test
    void divisionInto() {
    }

}