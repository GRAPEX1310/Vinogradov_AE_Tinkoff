package edu.hw8;

import edu.hw8.Task2.FibonacciNumber;
import edu.hw8.Task2.FixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {

    @Test
    @DisplayName("Check FixedThreadPool work")
    void testFixedThreadPool() {
        FixedThreadPool fixedThreadPool = new FixedThreadPool(6);

        fixedThreadPool.start();
        fixedThreadPool.execute(new FibonacciNumber(1));
        fixedThreadPool.execute(new FibonacciNumber(2));
        fixedThreadPool.execute(new FibonacciNumber(3));
        fixedThreadPool.execute(new FibonacciNumber(10));
        fixedThreadPool.execute(new FibonacciNumber(13));

        try {
            fixedThreadPool.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
