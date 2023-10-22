package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DictionaryTest {

    @Test
    @DisplayName("Checking getRandomWord efficiency")
    void getCorrectStringTest() {
        for (int iterator = 0; iterator < 4; iterator++) {
            String newString = new Dictionary().getRandomWord();
            assertThat(newString == null).isEqualTo(false);
            assertThat(newString.isEmpty()).isEqualTo(false);
        }
    }
}
