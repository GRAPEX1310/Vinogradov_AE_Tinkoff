package edu.hw7.Task4Tests;

import edu.hw7.Task4.LinearMonteCarloAlgorithm;
import edu.hw7.Task4.MultiThreadingMonteCarloAlgorithm;
import org.junit.jupiter.api.DisplayName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MonteCarloAlgorithmsTimeTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(MonteCarloAlgorithmsTimeTest.TimeTestArgumentsProvider.class)
    @DisplayName("Comparative time test of algorithms")
    void comparativeTimeTestMonteCarloAlgorithm(int attempts, int threads) {

        LinearMonteCarloAlgorithm.linearSolvingPi(attempts);
        long t1 = LinearMonteCarloAlgorithm.getTime();

        MultiThreadingMonteCarloAlgorithm.multiThreadingSolvingPi(attempts, threads);
        long t2 = MultiThreadingMonteCarloAlgorithm.getTime();

        LOGGER.info(t1);
        LOGGER.info(t2);
        LOGGER.info("Difference: " + (double) t1 / t2);

        assertThat(t1).isGreaterThan(t2);
    }

    static class TimeTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(1000, 6),
                    Arguments.of(100_000, 6),
                    Arguments.of(1_000_000, 6)
            );
        }
    }
}
