package edu.hw7;

import java.util.stream.IntStream;

public class Task2 {

    private Task2() {

    }

    public static int factorial(int n) {
        return IntStream.iterate(1, streamInteger -> streamInteger + 1)
                .limit(n)
                .parallel()
                .reduce((operand1, operand2) -> operand1 * operand2).orElse(1);
    }
}
