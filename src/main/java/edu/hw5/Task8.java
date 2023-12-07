package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {
    private Task8() {

    }


    //regular 1
    public static boolean oddLengthValidation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[0,1]([0,1]{2})*$");
        Matcher stringMatcher = pattern.matcher(givenString);
        return stringMatcher.find();
    }

    //regular 2
    public static boolean zeroOddAndOneEvenValidation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^0([0,1]{2})*$|^1([0,1]{3})*$");
        Matcher stringMatcher = pattern.matcher(givenString);
        return stringMatcher.find();
    }

    //regular 3
    public static boolean zeroDividedBy3Validation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^1*((1*01*){3})*1*$");
        Matcher stringMatcher = pattern.matcher(givenString);
        return stringMatcher.find();
    }

    //regular 4
    public static boolean ex11Ex111Validation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^(?!11$)(?!111$)[0-1]*$");
        Matcher stringMatcher = pattern.matcher(givenString);

        return stringMatcher.find();
    }

    //regular 5
    public static boolean oddOneValidation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^1([0-1](1)?)*$");
        Matcher stringMatcher = pattern.matcher(givenString);

        return stringMatcher.find();
    }

    //regular 6
    public static boolean moreThan2ZerosAndLessThan2OnesValidation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^0{2,}$|^0{2,}10*$|^0*10{2,}$|^0+10+$");
        Matcher stringMatcher = pattern.matcher(givenString);

        return stringMatcher.find();
    }

    //regular 7
    public static boolean withoutConsecutive1Validation(String givenString) {
        if (givenString == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^0*((10+)*1?)?$");
        Matcher stringMatcher = pattern.matcher(givenString);

        return stringMatcher.find();
    }
}
