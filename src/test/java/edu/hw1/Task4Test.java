package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Тест программы 4")
    void testFixString() {
        String example1 = null;
        String example2 = "s";
        String example3 = "oN";
        String example4 = "badce";
        String example5 = "apamig";

        String[] writeAnswerArray = {
                Task4.fixString(example1),
                Task4.fixString(example2),
                Task4.fixString(example3),
                Task4.fixString(example4),
                Task4.fixString(example5)
        };

        assertThat(writeAnswerArray).containsExactly(null, "s", "No", "abcde", "pamagi");
    }
}
