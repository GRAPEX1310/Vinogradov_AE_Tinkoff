package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

public class Task7 {
    public Task7() {

    }

    public TreeMap<String, String> tree = new TreeMap<>(Comparator.nullsFirst(Comparator.naturalOrder()));
}
