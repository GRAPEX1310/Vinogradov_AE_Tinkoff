package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Тест программы 7 сдвиг вправо")
    void testRotateRight() {

        //data for test 1
        int numberExample1 = 0;
        int shiftExample1 = 0;

        //data for test 2
        int numberExample2 = 1;
        int shiftExample2 = 1;

        //data for test 3
        int numberExample3 = 8;
        int shiftExample3 = 1;

        //data for test 4
        int numberExample4 = 15;
        int shiftExample4 = 1;

        //data for test 5
        int numberExample5 = 10;
        int shiftExample5 = 4;
        int[] writeAnswerArray = {
                Task7.rotateRight(numberExample1, shiftExample1),
                Task7.rotateRight(numberExample2, shiftExample2),
                Task7.rotateRight(numberExample3, shiftExample3),
                Task7.rotateRight(numberExample4, shiftExample4),
                Task7.rotateRight(numberExample5, shiftExample5)


        };

        assertThat(writeAnswerArray).containsExactly(0, 1, 4, 15, 10);
    }

    @Test
    @DisplayName("Тест программы 7 сдвиг влево")
    void testRotateLeft() {

        //data for test 1
        int numberExample1 = 0;
        int shiftExample1 = 0;

        //data for test 2
        int numberExample2 = 1;
        int shiftExample2 = 1;

        //data for test 3
        int numberExample3 = 8;
        int shiftExample3 = 1;

        //data for test 4
        int numberExample4 = 15;
        int shiftExample4 = 1;

        //data for test 5
        int numberExample5 = 10;
        int shiftExample5 = 4;
        int[] writeAnswerArray = {
                Task7.rotateLeft(numberExample1, shiftExample1),
                Task7.rotateLeft(numberExample2, shiftExample2),
                Task7.rotateLeft(numberExample3, shiftExample3),
                Task7.rotateLeft(numberExample4, shiftExample4),
                Task7.rotateLeft(numberExample5, shiftExample5)


        };

        assertThat(writeAnswerArray).containsExactly(0, 1, 1, 15, 10);
    }
}
