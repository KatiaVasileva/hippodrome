import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    @DisplayName("when null name throw illegal argument exception")
    void whenNullName_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.4));
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