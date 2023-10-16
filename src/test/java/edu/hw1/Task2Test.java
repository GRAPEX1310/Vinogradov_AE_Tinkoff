package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Тест программы 2")
    void testCountDigits() {
        int example1 = 0;
        int example2 = -1;
        int example3 = 1;
        int example4 = 634543;
        int example5 = -54668;

        int[] writeAnswersArray = {
                Task2.countDigits(example1),
                Task2.countDigits(example2),
                Task2.countDigits(example3),
                Task2.countDigits(example4),
                Task2.countDigits(example5)
        };

        assertThat(writeAnswersArray).containsExactly(1, 1, 1, 6, 5);
    }
}
