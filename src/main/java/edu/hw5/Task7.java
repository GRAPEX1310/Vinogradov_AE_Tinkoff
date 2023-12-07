package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {
    private Task7() {

    }

    public static boolean firstValidation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[0,1]{2}0[0,1]*$");
        Matcher stringMatcher = pattern.matcher(givenString);
        return stringMatcher.find();
    }

    public static boolean secondValidation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^(1[0-1]*1)$|^(0[0-1]*0)$|^0$|^1$");
        Matcher stringMatcher = pattern.matcher(givenString);
        return stringMatcher.find();
    }

    public static boolean thirdValidation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[0-1]{1,3}$");
        Matcher stringMatcher = pattern.matcher(givenString);
        return stringMatcher.find();
    }
}
