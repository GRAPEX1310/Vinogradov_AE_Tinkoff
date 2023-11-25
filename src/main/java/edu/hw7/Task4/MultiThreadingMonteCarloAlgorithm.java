package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import static edu.hw7.Task4.Utils.isInCircle;

public class MultiThreadingMonteCarloAlgorithm {

    private MultiThreadingMonteCarloAlgorithm() {
    }

    private static final int PARAM_1 = 4;
    private static final double PARAM_2 = 1.0;

    private static AtomicInteger totalCounter = new AtomicInteger(0);
    private static AtomicInteger circleCounter = new AtomicInteger(0);

    private static double piResult;

    private static long resultTime;

    static Consumer<Integer> consumer = iterations -> {
        AtomicInteger circleCount = new AtomicInteger(1);
        for (int i = 0; i < iterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            totalCounter.incrementAndGet();

            if (isInCircle(x, y)) {
                circleCounter.incrementAndGet();
            }
        }
    };

    public static void multiThreadingSolvingPi(int attempts, int threadsAmount) {

        long startTime = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>(threadsAmount);
        final int threadAttempts = attempts / threadsAmount;

        for (int i = 0; i < threadsAmount; i++) {
            threads.add(getExecutorThread(threadAttempts));
        }

        for (int i = 0; i < threadsAmount; i++) {
            threads.get(i).start();
        }

        try {
            for (int i = 0; i < threadsAmount; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        resultTime = System.currentTimeMillis() - startTime;

        piResult = PARAM_1 * (PARAM_2 * circleCounter.intValue() / totalCounter.intValue());
    }

    private static Thread getExecutorThread(int iterations) {
        return new Thread(() -> consumer.accept(iterations));
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
