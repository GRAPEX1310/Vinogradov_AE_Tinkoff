package edu.hw3;

import java.util.Map;

public class Task4 {
    private Task4() {

    }

    private static final int BASE = 10;

    private static final Map<Integer, String> ROMAN_NUMBERS = Map.ofEntries(
            Map.entry(1, "I"), Map.entry(4, "IV"), Map.entry(5, "V"), Map.entry(9, "IX"),
            Map.entry(10, "X"), Map.entry(40, "XL"), Map.entry(50, "L"), Map.entry(90, "XC"),
            Map.entry(100, "C"), Map.entry(400, "CD"), Map.entry(500, "D"), Map.entry(900, "CM"),
            Map.entry(1000, "M"));

    public static String convertToRoman(int givenNumber) {
        StringBuilder resultRomanNumber = new StringBuilder();
        int digits = Integer.toString(givenNumber).length();

        int number = givenNumber;

        int i = 0;

        while (i < digits) {

            StringBuilder currentRomanNumber = new StringBuilder();
            int currentNumber = (number % BASE) * (int) (Math.pow(BASE, i));

            number /= BASE;

            if (currentNumber == 0) {
                i++;
                continue;
            }

            for (int j = currentNumber; j >= 1; j--) {
                if (ROMAN_NUMBERS.containsKey(j)) {

                    int countNumber = currentNumber / j;
                    for (int k = 0; k < countNumber; k++) {
                        currentRomanNumber.append(ROMAN_NUMBERS.get(j));
                    }

                    int newNumber = currentNumber % j;

                    for (int k = 0; k < newNumber / Math.pow(BASE, i); k++) {
                        currentRomanNumber.append(ROMAN_NUMBERS.get((int) (Math.pow(BASE, i))));
                    }
                    break;
                }
            }
            if (i > 0) {
                resultRomanNumber.reverse().append(currentRomanNumber.reverse()).reverse();
            } else {
                resultRomanNumber.append(currentRomanNumber);
            }
            i++;
        }

        return resultRomanNumber.toString();
    }

}
