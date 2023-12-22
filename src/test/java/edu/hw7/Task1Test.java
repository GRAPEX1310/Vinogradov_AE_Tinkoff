package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task1Test.TestArgumentsProvider.class)
    @DisplayName("Checking AtomicCounter efficiency")
    void testAtomicCounter(int attempts, int result) {
        assertThat(Task1.antiRaceCounter(attempts)).isEqualTo(result);
    }


    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(10, 20),
                    Arguments.of(50, 100),
                    Arguments.of(1000, 2000),
                    Arguments.of(5000, 10000),
                    Arguments.of(0, 0)
            );
        }
    }
}
