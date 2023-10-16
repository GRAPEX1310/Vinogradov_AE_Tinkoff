package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String givenString) {
        if (givenString == null) {
            return null;
        }
        char[] lettersArray = givenString.toCharArray();

        for (int letterIndex = 1; letterIndex < lettersArray.length; letterIndex += 2) {
            char buffer = lettersArray[letterIndex - 1];
            lettersArray[letterIndex - 1] = lettersArray[letterIndex];
            lettersArray[letterIndex] = buffer;
        }
        return new String(lettersArray);
    }
}
