package edu.hw9.Task1;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class StatsCollector {

    private final BlockingQueue<Metric> dataCollector;

    public StatsCollector(int size) {
        dataCollector = new LinkedBlockingDeque<>(size);
    }

    public void push(String metricName, double[] stats) {
        try {
            dataCollector.put(new Metric(metricName, stats));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String stats() {
        Metric currentMetric;
        try {
            currentMetric = dataCollector.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return List.of(
                currentMetric.getName(),
                Double.toString(currentMetric.valuesSum()),
                Double.toString(currentMetric.averageValue()),
                Double.toString(currentMetric.maxValue()),
                Double.toString(currentMetric.minValue())
        ).toString();
    }
}
