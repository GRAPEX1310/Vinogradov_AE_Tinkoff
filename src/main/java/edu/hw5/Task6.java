package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private Task6() {
    }

    public static boolean substringSearch(String text, String string) {
        if (string == null || text == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(".*" + string + ".*");
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }
}
