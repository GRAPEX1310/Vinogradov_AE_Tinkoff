package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;

public class Task5 {
    private Task5() {
    }

    public static ArrayList<String> parseContacts(ArrayList<String> givenArray, String sortType) {
        if (givenArray == null || givenArray.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<String> resultArray = new ArrayList<>();

        for (int index = 0; index < givenArray.size(); index++) {
            String currentString = givenArray.get(index);
            String[] tokenArray = currentString.split(" ");
            if (tokenArray.length == 2) {
                String newString = tokenArray[1] + " " + tokenArray[0];
                givenArray.set(index, newString);
            }
        }

        if (sortType.equals("ASC")) {
            Collections.sort(givenArray);
        } else {
            givenArray.sort(Collections.reverseOrder());
        }

        for (int index = 0; index < givenArray.size(); index++) {
            String currentString = givenArray.get(index);
            String[] tokenArray = currentString.split(" ");
            if (tokenArray.length == 2) {
                String newString = tokenArray[1] + " " + tokenArray[0];
                givenArray.set(index, newString);
            }
        }

        return givenArray;
    }

}
