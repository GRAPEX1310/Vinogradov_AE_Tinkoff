package edu.hw9;

import edu.hw9.Task1.StatsCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Task1Test {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Check StatsCollector work")
    void testStatsCollector() {

        List<String> metricNames = List.of("a", "b", "c", "d", "e", "f");

        int size = 4;
        int metricsAmount = 10;
        StatsCollector collector = new StatsCollector(size);

        var send = Stream.generate(() -> CompletableFuture.runAsync(() ->
                        collector.push(
                                metricNames.get(ThreadLocalRandom.current().nextInt(metricNames.size())),
                                new double[] {ThreadLocalRandom.current().nextDouble(-5, 5),
                                        ThreadLocalRandom.current().nextDouble(-5, 5),
                                        ThreadLocalRandom.current().nextDouble(-5, 5),
                                        ThreadLocalRandom.current().nextDouble(-5, 5)}
                        ), Executors.newFixedThreadPool(size)))
                .limit(metricsAmount)
                .toArray(CompletableFuture[]::new);

        LOGGER.info("metricName     valuesSum       averageValue        maxValue        minValue\n");
        var answer = Stream.generate(() -> CompletableFuture.runAsync(
                        () ->
                                LOGGER.info(collector.stats()),
                        Executors.newFixedThreadPool(size)
                ))
                .limit(metricsAmount)
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(send).join();
        CompletableFuture.allOf(answer).join();

    }
}
