package edu.hw1;

public class Task3 {
    private Task3() {
    }

    private static long minArrayValue(long[] currentArray) {
        long minValue = Long.MAX_VALUE;

        for (long element : currentArray) {
            if (element < minValue) {
                minValue = element;
            }
        }

        return minValue;
    }

    private static long maxArrayValue(long[] currentArray) {
        long maxValue = Long.MIN_VALUE;

        for (long element : currentArray) {
            if (element > maxValue) {
                maxValue = element;
            }
        }
        return maxValue;
    }

    public static boolean isNestable(long[] nestableArray, long[] containArray) {
        if (nestableArray.length < 1 || containArray.length < 1) {
            return false;
        }

        if (minArrayValue(nestableArray) > minArrayValue(containArray)
                && maxArrayValue(nestableArray) < maxArrayValue(containArray)) {
            return true;
        }

        return false;
    }
}
