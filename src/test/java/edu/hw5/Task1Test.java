package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Checking averagePlayingTimeCounter function efficiency")
    void testAveragePlayingTimeCounter(ArrayList<String> intervals, Duration result) {
        assertThat(Task1.averagePlayingTimeCounter(intervals)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new ArrayList<>(Arrays.asList("2022-03-12, 23:50 - 2022-03-12, 20:20",
                            "2022-04-02, 01:20 - 2022-04-01, 23:50")), null),
                    Arguments.of(new ArrayList<>(Arrays.asList("2022-03-12, 20:20 - 2022-03-12, 23:50",
                            "2022-04-01, 21:30 - 2022-04-02, 01:20")), Duration.ofMinutes(3 * 60 + 40)),
                    Arguments.of(new ArrayList<>(Arrays.asList("2022-03-13, 20:20 - 2022-03-15, 23:50",
                            "2022-04-01, 21:30 - 2022-04-05, 01:20")), Duration.ofMinutes(2 * 30 * 60 + 3 * 60 + 40)),
                    Arguments.of(new ArrayList<>(Arrays.asList("2022-03-12, 23:50 - 2022-03-12, 20:20",
                            "2022-04-01, 21:30 - 2022-04-02, 01:20")), Duration.ofMinutes(3 * 60 + 50))
            );
        }
    }
}
