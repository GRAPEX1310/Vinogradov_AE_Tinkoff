package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(ArrayList<T> givenArray) {
        Map<T, Integer> resultFrequencyMap = new HashMap<>();
        for (int index = 0; index < givenArray.size(); index++) {
            resultFrequencyMap.merge(givenArray.get(index), 1, Integer::sum);
        }

        return resultFrequencyMap;
    }

}
