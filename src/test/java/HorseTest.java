import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    private static final String VALID_NAME = "Thunder";
    private static final double VALID_SPEED = 2.4;
    private static final double VALID_DISTANCE = 1.0;

    private Horse horse;
    private MockedStatic<Horse> mockedHorse;

    @BeforeEach
    void setUp() {
        horse = new Horse(VALID_NAME, VALID_SPEED, VALID_DISTANCE);
        mockedHorse = Mockito.mockStatic(Horse.class);
    }

    @Test
    @DisplayName("when null name throw illegal argument exception")
    void whenNullName_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, VALID_SPEED, VALID_DISTANCE));
    }

    @Test
    @DisplayName("when null name then correct message")
    void whenNullName_ThenCorrectMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(null, VALID_SPEED, VALID_DISTANCE));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("when blank name then throw illegal argument exception")
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", " \t "})
    void whenBlankName_ThenThrowIllegalArgumentException(String blankName) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, VALID_SPEED, VALID_DISTANCE));
    }

    @ParameterizedTest
    @DisplayName("when blank name then correct message")
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", " \t "})
    void whenBlankName_ThenCorrectMessage(String blankName) {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, VALID_SPEED, VALID_DISTANCE));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("when negative speed then throw illegal argument exception")
    void whenNegativeSpeed_ThenThrowIllegalArgumentException() {
        double invalidSpeed = -1.0;
        assertThrows(IllegalArgumentException.class, () -> new Horse(VALID_NAME, invalidSpeed, VALID_DISTANCE));
    }

    @Test
    @DisplayName("when negative speed then correct message")
    void whenNegativeSpeed_ThenCorrectMessage() {
        double invalidSpeed = -1.0;
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(VALID_NAME, invalidSpeed, VALID_DISTANCE));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("when negative distance then throw illegal argument exception")
    void whenNegativeDistance_ThenThrowIllegalArgumentException() {
        double invalidDistance = -1.0;
        assertThrows(IllegalArgumentException.class, () -> new Horse(VALID_NAME, VALID_SPEED, invalidDistance));
    }

    @Test
    @DisplayName("when negative distance then correct message")
    void whenNegativeDistance_ThenCorrectMessage() {
        double invalidDistance = -1.0;
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(VALID_NAME, VALID_SPEED, invalidDistance));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("when getName() then correct name from constructor")
    @ValueSource(strings = {"Thunder", "*Thunder-2*"})
    void whenGetName_ThenCorrectNameFromConstructor(String expectedName) {
        Horse horse = new Horse(expectedName, VALID_SPEED, VALID_DISTANCE);
        assertEquals(expectedName, horse.getName(), "getName() should return correct name from constructor");
    }

    @Test
    @DisplayName("when getSpeed() then return correct number from constructor")
    void whenGetSpeed_ThenReturnCorrectNumberFromConstructor() {
        assertEquals(VALID_SPEED, horse.getSpeed(), "getSpeed() should return correct number from constructor");
    }

    @Test
    @DisplayName("when getDistance() then return correct distance from constructor")
    void whenGetDistance_ThenReturnCorrectDistanceFromConstructor() {
        assertEquals(VALID_DISTANCE, horse.getDistance(), "getDistance() should return correct distance from constructor");
    }

    @Test
    @DisplayName("given constructor with two parameters when getDistance() then return zero")
    void givenConstructorWithTwoParameters_WhenGetDistance_ThenReturnZero() {
        double expectedDistance = 0.0;
        Horse horse = new Horse(VALID_NAME, VALID_SPEED);
        assertEquals(expectedDistance, horse.getDistance(), "getDistance() should return zero if two-parameter constructor is used");
    }

    @Test
    @DisplayName("when move() then getRandomDouble() is called")
    void whenMove_ThenGetRandomDoubleIsCalled() {
        horse.move();
        mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
    }

    @ParameterizedTest
    @DisplayName("when move() then calculate correct distance")
    @CsvSource({
            "2.4, 0.2, 0.0, 0.48",
            "2.5, 0.5, 1.0, 2.25",
            "3.0, 0.8, 3.0, 5.4"
    })
    void whenMove_ThenCalculateCorrectDistance(double speed, double random, double distance, double expectedDistance) {
        Horse horse = new Horse(VALID_NAME, speed, distance);
        mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
        horse.move();
        assertEquals(expectedDistance, horse.getDistance(), 0.001,
                "new distance should be calculated by formula: distance + speed * getRandomDouble(0.2, 0.9)");
        mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
    }

    @AfterEach
    void tearDown() {
        mockedHorse.close();
    }
}