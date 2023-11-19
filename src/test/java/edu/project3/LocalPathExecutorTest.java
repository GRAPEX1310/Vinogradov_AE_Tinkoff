package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LocalPathExecutorTest {

    @Test
    @DisplayName("Check LocalPathCheckerTest")
    void localPathCheckerTest() {
        LogAnalyzer logAnalyzer = new LogAnalyzer(null, null, null);

        LocalPathExecutor.checkPath(logAnalyzer, "test/*");

        assertThat(logAnalyzer.fileNames.stream().sorted().toArray()).containsExactly("test1.txt",
                "test2.txt", "test3.txt");

        assertThat(logAnalyzer.requestCount.intValue()).isEqualTo(3);

        assertThat(logAnalyzer.resources.values().stream().sorted().toArray()).containsExactly(1, 2);

        assertThat(logAnalyzer.resources.keySet().stream().sorted().toArray()).containsExactly("product_1",
                "product_2");

        assertThat(logAnalyzer.userAgent.keySet().stream().sorted().toArray()).containsExactly("Debian",
                "Maven");

        assertThat(logAnalyzer.requestSize.intValue()).isEqualTo(624);
    }
}