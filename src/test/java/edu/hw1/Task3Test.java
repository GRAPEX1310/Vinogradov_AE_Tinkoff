package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Тест программы 3")
    void testCountDigits() {
        //data for test 1
        long[] nestableArrayExample1 = {};
        long[] containArrayExample1 = {};

        //data for test 2
        long[] nestableArrayExample2 = {-10, 10};
        long[] containArrayExample2 = {-9, 0, 10};

        //data for test 3
        long[] nestableArrayExample3 = {0, 100};
        long[] containArrayExample3 = {0, 100};

        //data for test 4
        long[] nestableArrayExample4 = {6, 6, 6};
        long[] containArrayExample4 = {1, 2, 3};

        //data for test 5
        long[] nestableArrayExample5 = {13, 13, 13};
        long[] containArrayExample5 = {0, 20};

        boolean[] writeAnswerArray = {
                Task3.isNestable(nestableArrayExample1, containArrayExample1),
                Task3.isNestable(nestableArrayExample2, containArrayExample2),
                Task3.isNestable(nestableArrayExample3, containArrayExample3),
                Task3.isNestable(nestableArrayExample4, containArrayExample4),
                Task3.isNestable(nestableArrayExample5, containArrayExample5)
        };

        assertThat(writeAnswerArray).containsExactly(false, false, false, false, true);
    }
}
