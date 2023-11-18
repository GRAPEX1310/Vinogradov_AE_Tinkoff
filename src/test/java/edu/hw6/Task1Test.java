package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Test DiskMap working")
    void testDiskMap() {
        DiskMap diskMap = new DiskMap();

        diskMap.clear();
        assertThat(diskMap.size()).isEqualTo(0);
        assertThat(diskMap.isEmpty()).isEqualTo(true);
        assertThat(diskMap.containsKey("aaa")).isEqualTo(false);
        assertThat(diskMap.containsValue("bbb")).isEqualTo(false);

        diskMap.put("aaa", "bbb");
        diskMap.put("aaa", "bbb");
        assertThat(diskMap.size()).isEqualTo(1);
        diskMap.put("ddd", "fff");
        assertThat(diskMap.size()).isEqualTo(2);

        diskMap.remove("ddd");
        assertThat(diskMap.containsKey("aaa")).isEqualTo(true);

        diskMap.clear();
        diskMap.putAll(new HashMap<>(Map.ofEntries(
                new ImmutablePair<>("111", "1"),
                new ImmutablePair<>("222", "2"),
                new ImmutablePair<>("333", "3")
        )));
        assertThat(diskMap.values().toArray()).containsExactly("1", "2", "3");
        assertThat(Arrays.stream(
                diskMap.keySet().toArray()).sorted().toArray()).containsExactly("111", "222", "333");

        diskMap.clear();
    }
}
