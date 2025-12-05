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
        String expectedMessage = "Name cannot be null.";
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.4, 1.0));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @DisplayName("when empty name then throw illegal argument exception")
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", " \t "})
    void whenEmptyName_ThenThrowIllegalArgumentException(String emptyName) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(emptyName, 2.4, 1.0));
    }

    @ParameterizedTest
    @DisplayName("when blank name then correct message")
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", " \t "})
    void whenBlankName_ThenCorrectMessage(String blankName) {
        String expectedMessage = "Name cannot be blank.";
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, 2.4, 1.0));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("when negative speed then throw illegal argument exception")
    void whenNegativeSpeed_ThenThrowIllegalArgumentException() {
        double incorrectSpeed = -1.0;
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", incorrectSpeed, 1.0));
    }

    @Test
    @DisplayName("when negative speed then correct message")
    void whenNegativeSpeed_ThenCorrectMessage() {
        double incorrectSpeed = -1.0;
        String expectedMessage = "Speed cannot be negative.";
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse("name", incorrectSpeed, 1.0));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("when negative distance then throw illegal argument exception")
    void whenNegativeDistance_ThenThrowIllegalArgumentException() {
        double incorrectDistance = -1.0;
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2.4, incorrectDistance));
    }

    @Test
    @DisplayName("when negative distance then correct message")
    void whenNegativeDistance_ThenCorrectMessage() {
        double incorrectDistance = -1.0;
        String expectedMessage = "Distance cannot be negative.";
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2.4, incorrectDistance));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @DisplayName("when getName() then correct name from constructor")
    @ValueSource(strings = {"Thunder", "*Thunder-2*"})
    void whenGetName_ThenCorrectNameFromConstructor(String expectedName) {
        Horse horse = new Horse(expectedName, 2.4, 1.0);
        String actualName = horse.getName();
        assertEquals(expectedName, actualName, "getName() should return correct name from constructor");
    }

    @Test
    @DisplayName("when getSpeed() then return correct number from constructor")
    void whenGetSpeed_ThenReturnCorrectNumberFromConstructor() {
        double expectedSpeed = 2.4;
        Horse horse = new Horse("Thunder", expectedSpeed, 1.0);
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed, "getSpeed() should return correct number from constructor");
    }

    @Test
    @DisplayName("when getDistance() then return correct distance from constructor")
    void whenGetDistance_ThenReturnCorrectDistanceFromConstructor() {
        double expectedDistance = 1.0;
        Horse horse = new Horse("Thunder", 2.4, expectedDistance);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance, "getDistance() should return correct distance from constructor");
    }

    @Test
    @DisplayName("given constructor with two parameters when getDistance() then return zero")
    void givenConstructorWithTwoParameters_WhenGetDistance_ThenReturnZero() {
        double expectedDistance = 0.0;
        Horse horse = new Horse("Thunder", 2.4);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance, "getDistance() should return zero if two-parameter constructor is used");
    }


    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}