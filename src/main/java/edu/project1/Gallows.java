package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Gallows {

    private final static int MAX_ATTEMPTS = 10;

    private final static String GAME_RULES = """
            ==========RULES==========
            The meaning of the hangman game is guessing words
            I guess a word, and you have to send 1 letter, which, in your opinion, is in this word
            You have 10 attempts to guess the word
            For each wrong letter, the number of attempts will decrease by 1
            If you want to finish the game ahead of time, enter the word "stop". Good luck))""";

    private final static String CONGRATULATIONS = """
            ==========CONGRATULATIONS==========
            You guess the word and win this game!
            Thank you for playing!""";

    private final static String LOOSER_MESSAGE = """
            ==========DEFEAT==========
            You couldn't guess the word and you`ve lost this game!
            Thank you for playing!""";

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Scanner SCANNER = new Scanner(System.in);

    public void gameStart() {
        greetingMessage();

        Dictionary dictionary = new Dictionary();

        HiddenWord hiddenWord = new HiddenWord(dictionary.getRandomWord());
        UnknownWord unknownWord = new UnknownWord(hiddenWord);

        try {
            gameRound(hiddenWord, unknownWord);
        } catch (WordExceptions.GameEndingException gameEndingException) {
            LOGGER.info("You chose to finish the game");
            LOGGER.info("The write word is '" + hiddenWord.getWord() + "'.");
        }
    }

    private void gameRound(HiddenWord hiddenWord, UnknownWord unknownWord)
            throws WordExceptions.GameEndingException {

        int currentAttemptsCount = MAX_ATTEMPTS;

        LOGGER.info(unknownWord.getWord());

        while (currentAttemptsCount != 0) {
            LOGGER.info("You have " + Integer.toString(currentAttemptsCount) + " attempts\n");
            LOGGER.info("Your letter is: ");

            char currentSymbol = inputExecutor();

            if (currentSymbol == '\0') {
                throw new WordExceptions.GameEndingException();
            }

            boolean correctGuessing;
            try {
                correctGuessing = unknownWord.checkLetter(hiddenWord, currentSymbol);
            } catch (WordExceptions.WordHasThisLetterException wordHasThisLetterException) {
                LOGGER.info("You have already entered this letter. Try again!");
                continue;
            }

            if (correctGuessing) {
                LOGGER.info("Letter '" + currentSymbol + "' is in the hidden word. Good job");
            } else {
                LOGGER.info("Letter '" + currentSymbol + "' is not in the hidden word. Try again");
                currentAttemptsCount--;
            }

            LOGGER.info(unknownWord.getWord());

            if (isWordGuessed(unknownWord)) {
                LOGGER.info(CONGRATULATIONS);
                return;
            }
        }
        LOGGER.info(LOOSER_MESSAGE);
        LOGGER.info("The write word is '" + hiddenWord.getWord() + "'.");
    }


    private void greetingMessage() {
        LOGGER.info("Welcome to the Gallows Game!");
        LOGGER.info(GAME_RULES);
        LOGGER.info("==========LET`S ROCK!==========");
    }

    private char inputExecutor() {
        String userAnswer = "";
        boolean correctInput = false;
        char resultInputSymbol = ' ';

        while (true) {
            userAnswer = SCANNER.nextLine();

            if (userAnswer.length() == 1) {
                correctInput = true;
                resultInputSymbol = userAnswer.charAt(0);
            } else if (userAnswer.equals("stop")) {
                correctInput = true;
                return '\0';
            }

            if (!correctInput) {
                LOGGER.info("Incorrect input. Please, try to do it again. Write only ONE letter");
            } else {
                return resultInputSymbol;
            }
        }
    }

    private boolean isWordGuessed(UnknownWord unknownWord) {
        for (int iterator = 0; iterator < unknownWord.getWord().length(); iterator++) {
            if (unknownWord.getWord().charAt(iterator) == '*') {
                return false;
            }
        }
        return true;
    }
}
