package edu.hw7.Task4;

import java.security.SecureRandom;
import static edu.hw7.Task4.Utils.isInCircle;

public class LinearMonteCarloAlgorithm {

    private LinearMonteCarloAlgorithm() {
    }

    private static final int PARAM_1 = 4;
    private static final double PARAM_2 = 1.0;

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

        piResult = PARAM_1 * (PARAM_2 * circleCounter / totalCounter);
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
