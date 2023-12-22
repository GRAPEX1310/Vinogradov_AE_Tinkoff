package edu.hw7.Task4Tests;

import edu.hw7.Task4.MultiThreadingMonteCarloAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MonteCarloAlgorithmsErrorRateTest {

    private static final double ERROR_RATE_1 = 0.01;

    private static final Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(MonteCarloAlgorithmsErrorRateTest.MathErrorRateTestArgumentsProvider.class)
    @DisplayName("Math error rate test of algorithms")
    void testMathErrorRateMonteCarloAlgorithm(int attempts, int threads) {

        MultiThreadingMonteCarloAlgorithm.multiThreadingSolvingPi(attempts, threads);
        double result = MultiThreadingMonteCarloAlgorithm.getPiResult();
        double errorRate = MultiThreadingMonteCarloAlgorithm.getMathErrorRate();

        LOGGER.info(result);
        LOGGER.info(errorRate);
        assertThat(errorRate).isLessThan(ERROR_RATE_1);
    }

    static class MathErrorRateTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(10_000_000, 6),
                    Arguments.of(100_000_000, 6),
                    Arguments.of(1_000_000_000, 6)
            );
        }
    }
}
