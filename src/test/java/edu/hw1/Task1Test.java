package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Тест программы 1")
    void testPositiveMinutesToSeconds() {
        String example1 = "00:00";
        String example2 = "00:100";
        String example3 = "-100:10";
        String example4 = "100:10";
        String example5 = "12:34:56";

        Long[] writeAnswersArray = {
                Task1.minutesToSeconds(example1),
                Task1.minutesToSeconds(example2),
                Task1.minutesToSeconds(example3),
                Task1.minutesToSeconds(example4),
                Task1.minutesToSeconds(example5)
        };
        assertThat(writeAnswersArray).containsExactly(0L, -1L, -1L, 6010L, -1L);
    }
}
