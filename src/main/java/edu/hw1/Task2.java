package edu.hw1;


public class Task2 {

    private Task2() {
    }

    final static int BASE = 10;

    public static int countDigits(int givenNumber) {
        if (givenNumber == 0) {
            return 1;
        }

        int unsignedGivenNumber = Math.abs(givenNumber);

        int digits = 0;

        while (unsignedGivenNumber > 0) {
            digits++;
            unsignedGivenNumber /= BASE;
        }

        return digits;
    }
}
