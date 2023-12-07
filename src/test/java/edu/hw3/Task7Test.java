package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeMap;

import static  org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class Task7Test {

    @Test
    @DisplayName("Checking work of TreeMap with comparator")
    void tryTreeMap() {
        Task7 testClass = new Task7();
        testClass.tree.put(null, "test");

        assertThat(testClass.tree.containsKey(null)).isTrue();
    }
}
