package edu.project3;

import edu.project3.LogExecutors.LogExecutor;
import edu.project3.LogExecutors.URLExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class URLExecutorTest {

    @Test
    @DisplayName("Проверка работы URLChecker")
    void urlCheckerTest() {
        LogAnalyzer logAnalyzer = new LogAnalyzer(LocalDate.of(2015, 6, 3),
                LocalDate.of(2015, 6, 3), null);
        LogExecutor urlExecutor = new URLExecutor();
        urlExecutor.executeDataByPath(logAnalyzer,
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");

        assertThat(logAnalyzer.requestCount.intValue()).isEqualTo(2816);
        assertThat(logAnalyzer.resources.keySet().stream().sorted().toArray()).containsExactly("product_1",
                "product_2", "product_3");
        assertThat(logAnalyzer.resources.values().stream().sorted().toArray()).containsExactly(5,
                1138, 1673);

        assertThat(logAnalyzer.codes.get(404)).isEqualTo(1891);
        assertThat(logAnalyzer.fileNames.toArray()).containsExactly("/nginx_logs");
    }
}