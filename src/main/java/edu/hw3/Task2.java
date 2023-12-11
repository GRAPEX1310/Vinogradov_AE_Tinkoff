package edu.hw3;

import java.util.ArrayList;

public class Task2 {
    private Task2() {

    }

    public static ArrayList<String> clusterize(String givenString) {
        ArrayList<String> resultArray = new ArrayList<>();

        int brackersCounter = 0;

        StringBuilder currentString = new StringBuilder();

        for (int index = 0; index < givenString.length(); index++) {
            if (givenString.charAt(index) == '(') {
                brackersCounter++;
            } else {
                brackersCounter--;
            }

            currentString.append(givenString.charAt(index));

            if (brackersCounter == 0) {
                resultArray.add(currentString.toString());
                currentString.delete(0, currentString.length());
            }
        }

        return resultArray;
    }
}
