package edu.hw1;

public class Task1 {

    private Task1() {
    }

    private static final int SECONDS_IN_MINUTE = 60;

    public static long minutesToSeconds(String videoDuration) {

        String[] tokenArray = videoDuration.split(":");

        if (Long.parseLong(tokenArray[1]) >= SECONDS_IN_MINUTE || Long.parseLong(tokenArray[0]) < 0
                || Long.parseLong(tokenArray[1]) < 0 || tokenArray.length != 2) {
            return -1;
        }

        return Long.parseLong(tokenArray[1]) + Long.parseLong(tokenArray[0]) * SECONDS_IN_MINUTE;
    }
}
