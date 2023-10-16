package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    @DisplayName("Тест программы 6")
    void testCountK() {
        int example1 = 1;
        int example2 = 3524;
        int example3 = 6621;
        int example4 = -6621;
        int example5 = 1234;

        int[] writeAnswerArray = {
                Task6.countK(example1),
                Task6.countK(example2),
                Task6.countK(example3),
                Task6.countK(example4),
                Task6.countK(example5),

        };

        assertThat(writeAnswerArray).containsExactly(-1, 3, 5, -1, 3);
    }
}
