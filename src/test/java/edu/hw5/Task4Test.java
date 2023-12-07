package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task4Test.TestArgumentsProvider.class)
    @DisplayName("Checking isPasswordCorrect function efficiency")
    void testIsPasswordCorrect(String password, boolean result) {
        assertThat(Task4.isPasswordCorrect(password)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("java_Best", false),
                    Arguments.of("~tinkoff#Password1337!", true),
                    Arguments.of("@!~sus", true),
                    Arguments.of(null, false)
            );
        }
    }
}
