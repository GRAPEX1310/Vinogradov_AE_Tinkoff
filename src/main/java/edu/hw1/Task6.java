package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private Task6() {
    }

    final static int MIN_VAL = 1000;
    final static int CYCLE_LIMIT = 7;
    final static int REFERENCE_NUMBER = 6174;


    private static int cyclesCount = 0;

    public static int countK(int givenNumber) {
        if (givenNumber < MIN_VAL) {
            return -1;
        }


        cyclesCount++;

        if (cyclesCount == CYCLE_LIMIT) {
            cyclesCount = 0;
            return -CYCLE_LIMIT;
        }

        char[] digitsArray = Integer.toString(givenNumber).toCharArray();
        Arrays.sort(digitsArray);
        int lowerSortedNumber = Integer.parseInt(new String(digitsArray));
        int upperSortedNumber = Integer.parseInt(new StringBuilder(new String(digitsArray)).reverse().toString());

        upperSortedNumber -= lowerSortedNumber;

        if (upperSortedNumber == REFERENCE_NUMBER) {
            cyclesCount = 0;
            return 1;
        } else {
            return 1 + countK(upperSortedNumber);
        }
    }
}
