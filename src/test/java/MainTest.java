import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("when start then main() should not exceed 22 sec")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Test is disabled by default so that it doesn't slow down building. Start manually when required")
    void whenStart_ThenMainShouldNotExceed22Seconds() {
        assertDoesNotThrow(() -> Main.main(new String[]{}),
                "main() exceeds 22 seconds or throws exception");
    }
}