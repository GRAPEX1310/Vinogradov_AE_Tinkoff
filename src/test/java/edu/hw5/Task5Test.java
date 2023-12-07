package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task5Test.TestArgumentsProvider.class)
    @DisplayName("Checking isCarNumberCorrect function efficiency")
    void testIsCarNumberCorrect(String carNumber, boolean result) {
        assertThat(Task5.isCarNumberCorrect(carNumber)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("123АВЕ777", false),
                    Arguments.of("А123ВГ77", false),
                    Arguments.of("А123ВЕ7777", false),
                    Arguments.of("А123ВЕ777", true),
                    Arguments.of("О777ОО177", true),
                    Arguments.of(null, false)
            );
        }
    }
}
