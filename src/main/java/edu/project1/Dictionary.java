package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;

public class Dictionary {
    public Dictionary() {

    }

    private final static int NUMBERS_SYSTEM_SIZE = 10;

    private final static ArrayList<String> DICTIONARY = new ArrayList<>(Arrays.asList(
            "java", "tinkoff", "science", "computer", "gallows"
    ));

    public String getRandomWord() {
        int randomNumber = ((int) (Math.random() * NUMBERS_SYSTEM_SIZE)) % DICTIONARY.size();
        return DICTIONARY.get(randomNumber);
    }

}
