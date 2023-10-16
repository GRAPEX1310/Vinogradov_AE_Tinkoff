package edu.hw1;

public class Task5 {

    private Task5() {
    }

    final static int BASE = 10;

    private static boolean isPalindrome(char[] lettersArray) {
        int arrayLength = lettersArray.length;
        if (arrayLength < 2) {
            return false;
        }

        for (int i = 0; i < (arrayLength / 2); ++i) {
            if (lettersArray[i] != lettersArray[arrayLength - i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static char[] countDescendant(char[] lettersArray) {
        String descendantNumber = "";
        for (int i = 1; i < lettersArray.length; i += 2) {
            int firstDigit = Character.getNumericValue(lettersArray[i - 1]);
            int secondDigit = Character.getNumericValue(lettersArray[i]);
            descendantNumber += Integer.toString(firstDigit + secondDigit);
        }
        return descendantNumber.toCharArray();
    }

    public static boolean isPalindromeDescendant(long givenNumber) {

        long unsignedGivenNumber = Math.abs(givenNumber);

        String currentNumber = Long.toString(unsignedGivenNumber);
        char[] lettersArray = currentNumber.toCharArray();

        if (unsignedGivenNumber < BASE || (lettersArray.length % 2 != 0 && !isPalindrome(lettersArray))) {
            return false;
        }


        if (isPalindrome(lettersArray)) {
            return true;
        }

        while (lettersArray.length >= 2) {
            lettersArray = countDescendant(lettersArray);

            if (isPalindrome(lettersArray)) {
                return true;
            }
        }

        return false;
    }
}
