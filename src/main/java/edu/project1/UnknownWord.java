package edu.project1;

import java.util.Vector;
import org.apache.commons.lang3.StringUtils;

public class UnknownWord extends BaseWord {
    public UnknownWord(HiddenWord givenWord) {
        super(StringUtils.repeat('*', givenWord.getWord().length()));
    }

    boolean checkLetter(HiddenWord hiddenWord, char currentSymbol)
            throws WordExceptions.WordHasThisLetterException {
        Vector<Integer> letterPositions = hiddenWord.checkLetterPositions(currentSymbol);

        if (letterPositions.isEmpty()) {
            return false;
        }

        StringBuilder newString = new StringBuilder(getWord());

        for (int iterator = 0; iterator < letterPositions.size(); iterator++) {
            if (getWord().charAt(letterPositions.get(iterator)) != '*') {
                throw new WordExceptions.WordHasThisLetterException();
            } else {
                newString.setCharAt(letterPositions.get(iterator), currentSymbol);
            }
        }

        setWord(newString.toString());
        return true;
    }

}
