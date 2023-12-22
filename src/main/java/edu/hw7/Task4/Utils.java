package edu.hw7.Task4;

public class Utils {

    private Utils() {
    }

    private static final int PARAM_3 = 2;
    private static final double CENTER_COORDS = 0.5;
    private static final double RADIUS = 0.5;

    public static boolean isInCircle(double x, double y) {
        return distanceBetweenDots(CENTER_COORDS, CENTER_COORDS, x, y) <= RADIUS;
    }

    private static double distanceBetweenDots(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, PARAM_3) + Math.pow(y1 - y2, PARAM_3));
    }
}
