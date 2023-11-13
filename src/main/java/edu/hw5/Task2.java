package edu.hw5;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Task2 {

    private static final int SCARY_NUMBER = 13;
    private static final int YEAR_SUMMAND = 1900;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Task2() {

    }

    public static ArrayList<String> scaryFridaySearch(int year) {
        ArrayList<String> resultArray = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int currentMonth = 0;

        while (currentMonth < SCARY_NUMBER - 1) {
            calendar.set(year, currentMonth, SCARY_NUMBER);

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                resultArray.add(DATE_FORMAT.format(new Date(year - YEAR_SUMMAND, currentMonth, SCARY_NUMBER)));
            }

            currentMonth++;
        }

        return resultArray;
    }

    public static LocalDate nearestScaryDay(LocalDate today) {
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(currentDate -> {
            ArrayList<String> scaryFridaysOfThisYear = scaryFridaySearch(currentDate.getYear());

            for (int index = 0; index < scaryFridaysOfThisYear.size() - 1; index++) {
                if (LocalDate.parse(scaryFridaysOfThisYear.get(index)).equals(currentDate)) {
                    return LocalDate.parse(scaryFridaysOfThisYear.get(index + 1));
                }
            }

            for (int index = 1; ; index++) {
                scaryFridaysOfThisYear = scaryFridaySearch(currentDate.getYear() + index);
                if (!scaryFridaysOfThisYear.isEmpty()) {
                    return LocalDate.parse(scaryFridaysOfThisYear.getFirst());
                }
            }

        });

        return today.with(temporalAdjuster);
    }
}
