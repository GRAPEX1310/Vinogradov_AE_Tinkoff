package edu.hw3;

/*
 * a 97
 * z 122
 * A 65
 * Z 90
 * */
public class Task1 {
    private Task1() {
    }

    private static final int ASCII_CODE_A = 65;
    private static final int ASCII_CODE_Z = 90;
    private static final int ASCII_CODE_A_LITTLE = 97;
    private static final int ASCII_CODE_Z_LITTLE = 122;
    private static final int ALPHABET_DIFF = 25;

    public static String atBash(String givenString) {
        char[] lettersArray = givenString.toCharArray();

        for (int index = 0; index < lettersArray.length; index++) {
            if (lettersArray[index] >= ASCII_CODE_A && lettersArray[index] <= ASCII_CODE_Z) {
                lettersArray[index] = (char) (ALPHABET_DIFF - lettersArray[index] + 2 * ASCII_CODE_A);
            } else if (lettersArray[index] >= ASCII_CODE_A_LITTLE && lettersArray[index] <= ASCII_CODE_Z_LITTLE) {
                lettersArray[index] = (char) (ALPHABET_DIFF - lettersArray[index] + 2 * ASCII_CODE_A_LITTLE);
            }
        }
        return new String(lettersArray);
    }
}
