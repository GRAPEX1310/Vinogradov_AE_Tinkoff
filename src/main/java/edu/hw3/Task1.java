package edu.hw3;

public class Task1 {
    private Task1() {
    }

    private static final int ASCII_CODE_A = ((int) 'A');
    private static final int ASCII_CODE_Z = ((int) 'Z');
    private static final int ASCII_CODE_A_LITTLE = ((int) 'a');
    private static final int ASCII_CODE_Z_LITTLE = ((int) 'z');
    private static final int ALPHABET_DIFF = 25;


    public static String atBash(String givenString) {
        StringBuilder resultString = new StringBuilder(givenString);

        for (int index = 0; index < resultString.length(); index++) {
            if (resultString.charAt(index) >= ASCII_CODE_A && resultString.charAt(index) <= ASCII_CODE_Z) {
                resultString.setCharAt(index, (char) (ALPHABET_DIFF - resultString.charAt(index) + 2 * ASCII_CODE_A));
            } else if (resultString.charAt(index) >= ASCII_CODE_A_LITTLE
                    && resultString.charAt(index) <= ASCII_CODE_Z_LITTLE) {
                resultString.setCharAt(index, (char)
                        (ALPHABET_DIFF - resultString.charAt(index) + 2 * ASCII_CODE_A_LITTLE));
            }
        }
        return resultString.toString();
    }
}
