package edu.hw7.Task4;

import java.security.SecureRandom;
import static edu.hw7.Task4.Utils.isInCircle;

public class LinearMonteCarloAlgorithm {

    private LinearMonteCarloAlgorithm() {
    }

    private static final int FIRST_MULTIPLIER_OF_PI = 4;
    private static final double SECOND_MULTIPLIER_OF_PI = 1.0;

    private static long resultTime;
    private static double piResult;

    public static void linearSolvingPi(int attempts) {
        int totalCounter = 0;
        int circleCounter = 0;

        long startTime = System.currentTimeMillis();

        SecureRandom random = new SecureRandom();
        for (int index = 0; index < attempts; index++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            totalCounter++;

            if (isInCircle(x, y)) {
                circleCounter++;
            }
        }

        resultTime = System.currentTimeMillis() - startTime;

        piResult = FIRST_MULTIPLIER_OF_PI * (SECOND_MULTIPLIER_OF_PI * circleCounter / totalCounter);
    }

    public static double getPiResult() {
        return piResult;
    }

    public static long getTime() {
        return resultTime;
    }

    public static double getMathErrorRate() {
        return Math.abs(Math.PI - piResult);
    }
}
