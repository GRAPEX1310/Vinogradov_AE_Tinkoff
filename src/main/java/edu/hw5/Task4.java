package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    private Task4() {
    }

    public static boolean isPasswordCorrect(String givenPassword) {
        if (givenPassword == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[~!@#$%^&*|]");
        Matcher passwordMatcher = pattern.matcher(givenPassword);

        return passwordMatcher.find();
    }
}
