package edu.hw9.Task1;

import java.util.Arrays;

public class Metric {

    private String name;
    private double[] values;

    public Metric(String mName, double[] data) {
        name = mName;
        values = data;
    }

    public String getName() {
        return name;
    }

    public double[] getValues() {
        return values;
    }

    public double valuesSum() {
        return Arrays.stream(values).sum();
    }

    public double averageValue() {
        return Arrays.stream(values).average().orElse(0);
    }

    public double maxValue() {
        return Arrays.stream(values).max().orElse(0);
    }

    public double minValue() {
        return Arrays.stream(values).min().orElse(0);
    }
}
