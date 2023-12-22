package edu.hw8.Task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FibonacciNumber implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();

    private final int number;

    public FibonacciNumber(int number) {
        this.number = number;
    }

    public long countFibNumber(int number) {
        if (number < 1) {
            return 0;
        }
        if (number <= 2) {
            return 1;
        }
        return countFibNumber(number - 1) + countFibNumber(number - 2);
    }

    @Override
    public void run() {
        LOGGER.info("The result is = " + countFibNumber(number));
    }
}

