package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Personal fixedThreadPool check")
    void testPersonalFixedThreadPool() throws Exception {
        FixedThreadPool fixedThreadPool = new FixedThreadPool();
        fixedThreadPool.create(6);

        List<Integer> fibonacciNumbers = new ArrayList<>(Collections.nCopies(13, -1));

        List<Thread> threads = IntStream.range(0, 13).mapToObj(element -> new Thread(() -> {
            if (element == 0 || element == 1) {
                fibonacciNumbers.set(element, 1);
            } else {
                while (fibonacciNumbers.get(element - 1) == -1 || fibonacciNumbers.get(element - 2) == -1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                fibonacciNumbers.set(element, fibonacciNumbers.get(element - 1) + fibonacciNumbers.get(element - 2));
            }

        })).toList();

        for (Thread thread : threads) {
            fixedThreadPool.execute(thread);
        }

        fixedThreadPool.start();

        while (fibonacciNumbers.get(12) == -1) {
            Thread.sleep(10);
        }

        fixedThreadPool.close();

        assertThat(fibonacciNumbers.toArray()).containsExactly(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233);
    }
}
