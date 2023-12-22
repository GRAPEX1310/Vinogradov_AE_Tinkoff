package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    private Task1() {

    }

    private static AtomicInteger counter = new AtomicInteger(0);

    public int getValue() {
        return counter.intValue();
    }

    public static int antiRaceCounter(int incrementAmount) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < incrementAmount; i++) {
                counter.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < incrementAmount; i++) {
                counter.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int result = counter.intValue();

        counter = new AtomicInteger(0);
        return result;
    }
}
