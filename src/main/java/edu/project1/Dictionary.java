package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dictionary {
    public Dictionary() {

    }

    private final static int NUMBERS_SYSTEM_SIZE = 10;

    private final static ArrayList<String> DICTIONARY = new ArrayList<>(Arrays.asList(
            "java", "tinkoff", "science", "computer", "gallows"
    ));

    public String getRandomWord() {
        Random random = new Random();
        int randomNumber = random.nextInt(DICTIONARY.size());
        return DICTIONARY.get(randomNumber);
    }

}
