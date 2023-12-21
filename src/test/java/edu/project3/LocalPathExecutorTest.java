package edu.project3;

import edu.project3.LogExecutors.LocalPathExecutor;
import edu.project3.LogExecutors.LogExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LocalPathExecutorTest {

    @Test
    @DisplayName("Check LocalPathCheckerTest")
    void localPathCheckerTest() {
        LogAnalyzer logAnalyzer = new LogAnalyzer(null, null, null);
        LogExecutor localPathExecutor = new LocalPathExecutor();
        localPathExecutor.executeDataByPath(logAnalyzer, "test/*");

        assertThat(logAnalyzer.fileNames.stream().sorted().toArray()).containsExactly("test1.txt",
                "test2.txt", "test3.txt");

        assertThat(logAnalyzer.requestCount.intValue()).isEqualTo(3);

        Map<String, Integer> expectedResult = new HashMap<>(Map.ofEntries(
                Map.entry("product_1", 2),
                Map.entry("product_2", 1)
        ));

        assertThat(logAnalyzer.resources).isEqualTo(expectedResult);

        assertThat(logAnalyzer.resources.keySet().stream().sorted().toArray()).containsExactly("product_1",
                "product_2");

        assertThat(logAnalyzer.userAgent.keySet().stream().sorted().toArray()).containsExactly("Debian",
                "Maven");

        assertThat(logAnalyzer.requestSize.intValue()).isEqualTo(624);
    }
}