package edu.hw11.Task3;

public class FibCalculator {
    public FibCalculator() {
    }

    @SuppressWarnings("MagicNumber")
    public long fib(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }

        long[] fibonacciNumbers = new long[number + 1];

        for (int index = 2; index <= number; index++) {
            fibonacciNumbers[index] = fibonacciNumbers[index - 1] + fibonacciNumbers[index - 2];
        }

        return fibonacciNumbers[number];
    }
}
