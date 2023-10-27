package edu.hw3;

import java.util.ArrayList;

public class Task2 {
    private Task2() {

    }

    public static ArrayList<String> clusterize(String givenString) {
        ArrayList<String> resultArray = new ArrayList<>();

        int bracketsCounter = 0;
        char[] lettersArray = givenString.toCharArray();
        StringBuilder currentString = new StringBuilder();

        for (int i = 0; i < lettersArray.length; i++) {
            if (lettersArray[i] == '(') {
                bracketsCounter++;
            } else {
                bracketsCounter--;
            }

            currentString.append(lettersArray[i]);

            if (bracketsCounter == 0) {
                resultArray.add(new String(currentString));
                currentString.delete(0, currentString.length());
            }
        }

        return resultArray;
    }

}
