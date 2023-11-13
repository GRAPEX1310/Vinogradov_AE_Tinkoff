package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {

    private Task5() {
    }

    public static boolean isCarNumberCorrect(String givenCarNumber) {
        if (givenCarNumber == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^([А-Я])([0-9]{3})([А-Я]{2})([0-9]{3})$");
        Matcher carNumberMatcher = pattern.matcher(givenCarNumber);

        return carNumberMatcher.find();
    }

}
