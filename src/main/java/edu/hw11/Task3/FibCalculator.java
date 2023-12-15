package edu.hw11.Task3;

public class FibCalculator {
    private FibCalculator() {
    }

    @SuppressWarnings("MagicNumber")
    public static long fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int a = 0;
        int b = 1;
        int i = 1;

        while (i != n) {
            int temp = b;
            b = a + b;
            a = temp;
            i++;
        }

        return b;
    }
}
