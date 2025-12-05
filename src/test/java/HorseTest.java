import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    @DisplayName("when null name throw illegal argument exception")
    void whenNullName_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.4, 1.0));
    }

    @Test
    @DisplayName("when null name then correct message")
    void whenNullName_ThenCorrectMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.4, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("when empty name then throw illegal argument exception")
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", " \t "})
    void whenEmptyName_ThenThrowIllegalArgumentException(String str) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(str, 2.4, 1.0));
    }

    @ParameterizedTest
    @DisplayName("when empty name then correct message")
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", " \t "})
    void whenEmptyName_ThenCorrectMessage(String str) {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(str, 2.4, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("when negative speed then throw illegal argument exception")
    void whenNegativeSpeed_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -2.4, 1.0));
    }

    @Test
    @DisplayName("when negative speed then correct message")
    void whenNegativeSpeed_ThenCorrectMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse("name", -2.4, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("when negative distance then throw illegal argument exception")
    void whenNegativeDistance_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2.4, -1.0));
    }

    @Test
    @DisplayName("when negative distance then correct message")
    void whenNegativeDistance_ThenCorrectMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2.4, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }



    @Test
    void getName() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}