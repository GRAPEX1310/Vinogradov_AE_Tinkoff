package edu.hw9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {

    private final ExecutorService executorService;
    private final ReadWriteLock lock;
    private volatile double valuesSum;
    private volatile double valuesCount;
    private volatile double minValue;
    private volatile double maxValue;
    private static final int THREADS_AMOUNT = 6;

    public StatsCollector() {
        executorService = Executors.newFixedThreadPool(THREADS_AMOUNT);
        lock = new ReentrantReadWriteLock();
        valuesSum = 0;
        valuesCount = 0;
        minValue = Double.MAX_VALUE;
        maxValue = Double.MIN_VALUE;
    }

    public void push(String metricName, double[] stats) {
        for (double data: stats) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (StatsCollector.class) {
                        lock.writeLock().lock();
                        valuesSum += data;
                        valuesCount++;
                        minValue = Math.min(minValue, data);
                        maxValue = Math.max(maxValue, data);
                        lock.writeLock().unlock();
                    }
                }
            });
        }
    }

    public List<Map.Entry<String, Double>> stats() {
        lock.readLock().lock();
        List<Map.Entry<String, Double>> result = new ArrayList<>();
        try {
            Thread.sleep(1);
            result.add(Map.entry("Values sum = ", valuesSum));
            result.add(Map.entry("Average value = ", valuesSum / valuesCount));
            result.add(Map.entry("Min value = ", minValue));
            result.add(Map.entry("Max value = ", maxValue));
        } catch (InterruptedException error) {
            throw new RuntimeException();
        } finally {
            lock.readLock().unlock();
        }

        return result;
    }
}
