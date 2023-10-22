package edu.project1;

import java.util.Vector;

public class HiddenWord extends BaseWord {

    public HiddenWord(String givenWord) {
        super(givenWord);
    }

    public Vector<Integer> checkLetterPositions(char currentSymbol) {
        Vector<Integer> letterPositions = new Vector<>();

        for (int iterator = 0; iterator < getWord().length(); iterator++) {
            if (getWord().charAt(iterator) == currentSymbol) {
                letterPositions.add(iterator);
            }
        }

        return letterPositions;
    }

}
