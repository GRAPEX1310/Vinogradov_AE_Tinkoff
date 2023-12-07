package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    private Task1() {
    }

    private static final String TIME_PATTERN = "yyyy-MM-dd, HH:mm";

    public static Duration averagePlayingTimeCounter(ArrayList<String> givenArray) {
        Pattern timePattern = Pattern.compile("^(.*) - (.*)$");

        int sessionCount = 0;
        long secondsCount = 0;

        for (String session : givenArray) {
            Matcher timeMatcher = timePattern.matcher(session);

            if (timeMatcher.find() && timeMatcher.groupCount() == 2) {
                LocalDateTime firstDate = DateTimeFormatter.ofPattern(TIME_PATTERN)
                        .parse(timeMatcher.group(1), LocalDateTime::from);

                LocalDateTime secondDate = DateTimeFormatter.ofPattern(TIME_PATTERN)
                        .parse(timeMatcher.group(2), LocalDateTime::from);

                if (secondDate.isAfter(firstDate)) {
                    sessionCount++;
                    secondsCount += firstDate.until(secondDate, ChronoUnit.SECONDS);
                }
            }
        }

        if (sessionCount == 0) {
            return null;
        } else {
            return Duration.ofSeconds(secondsCount / sessionCount);
        }
    }
}
