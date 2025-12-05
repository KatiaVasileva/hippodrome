import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    private List<Horse> horses;
    private Hippodrome hippodrome;

    @BeforeEach
    void setUp() {
        horses = new ArrayList<>();
        IntStream.range(0, 30).forEach(i -> horses.add(new Horse("Horse_" + i, 1.0 + i * 0.1, i)));
        hippodrome = new Hippodrome(horses);
    }

    @Test
    @DisplayName("when null throw illegal argument exception")
    void whenNull_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("when null then correct message")
    void whenNullName_ThenCorrectMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("when empty throw illegal argument exception")
    void whenEmpty_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    @DisplayName("when empty then correct message")
    void whenEmptyName_ThenCorrectMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("when getHorses() then get all horses in same order")
    void whenGetHorses_ThenGetAllHorsesInSameOrder() {
        List<Horse> actualHorses = hippodrome.getHorses();
        assertEquals(horses.size(), actualHorses.size(), "Horses size should be equal");
        for (int i = 0; i < horses.size(); i++) {
            assertSame(horses.get(i), actualHorses.get(i), "Horse should be returned in the same order");
        }
    }

    @Test
    @DisplayName("when move() then call move for each horse")
    void whenMove_ThenCallMoveForEachHorse() {
        int horseNumber = 50;
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < horseNumber; i++) {
            mockHorses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        for (Horse horse : mockHorses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    @DisplayName("when getWinner() then get horse with max distance")
    void whenGetWinner_ThenGetHorseWithMaxDistance() {
        Horse slowHorse = new Horse("name2", 2.1, 9.0);
        Horse mediumHorse = new Horse("name3", 2.2, 10.0);
        Horse fastHorse = new Horse("name1", 2.3, 10.0);
        Horse winningHorse = new Horse("name4", 2.4, 12.0);

        List<Horse> horses = List.of(fastHorse, slowHorse, mediumHorse, winningHorse);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse actualWinningHorse = hippodrome.getWinner();
        assertSame(winningHorse, actualWinningHorse, "getWinner() should return horse with max distance)");
    }
}