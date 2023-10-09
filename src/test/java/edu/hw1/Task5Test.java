package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Тест программы 5")
    void testisPalindromeDescendant() {
        long example1 = 1;
        long example2 = 144;
        long example3 = 11211230;
        long example4 = -100;
        long example5 = -121;

        boolean[] writeAnswerArray = {
                Task5.isPalindromeDescendant(example1),
                Task5.isPalindromeDescendant(example2),
                Task5.isPalindromeDescendant(example3),
                Task5.isPalindromeDescendant(example4),
                Task5.isPalindromeDescendant(example5)
        };

        assertThat(writeAnswerArray).containsExactly(false, false, true, false, true);
    }
}