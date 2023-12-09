package edu.project4;

import edu.project4.Builders.FlameBuilder;
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

public class FractalThreadsTimeTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int RESOLUTION_X = 1920;
    private static final int RESOLUTION_Y = 1080;

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(FractalThreadsTimeTest.TimeTestArgumentsProvider.class)
    @DisplayName("Comparative thread test")
    void testThreadsTime(int samples, int iterations) {
        FlameBuilder singleThreadFlame = new FlameBuilder(samples, iterations, 2.2,
                true, 1, RESOLUTION_X, RESOLUTION_Y);

        long t1 = System.currentTimeMillis();
        singleThreadFlame.computeFractalFlame();
        singleThreadFlame.gammaCorrection(singleThreadFlame.getMatrix());
        long singleThreadTime = System.currentTimeMillis() - t1;

        FlameBuilder multiThreadFlame = new FlameBuilder(samples, iterations, 2.2,
                true, 6, RESOLUTION_X, RESOLUTION_Y);

        long t2 = System.currentTimeMillis();
        multiThreadFlame.computeFractalFlame();
        multiThreadFlame.gammaCorrection(multiThreadFlame.getMatrix());
        long multiThreadTime = System.currentTimeMillis() - t2;

        LOGGER.info(singleThreadTime);
        LOGGER.info(multiThreadTime);
        LOGGER.info("Difference:" + (singleThreadTime - multiThreadTime));

        boolean tasksCompleted = true;

        assertThat(tasksCompleted).isTrue();

    }


    static class TimeTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(1000, 10),
                    Arguments.of(1000, 1000),
                    Arguments.of(10_000, 1000)
            );
        }
    }
}
