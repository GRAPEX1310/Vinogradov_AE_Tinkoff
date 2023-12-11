package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task3Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task3Test.TestArgumentsProvider.class)
    @DisplayName("Checking freqDict function")
    <T> void testFreqDictFunction(ArrayList<T> givenArray, String writeAnswer) {
        assertThat(Task3.freqDict(givenArray).toString()).isEqualTo(writeAnswer);
    }


    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(new ArrayList<>(Arrays.asList(1, 1, 2, 2)), "{1=2, 2=2}"),
                    Arguments.of(
                            new ArrayList<>(Arrays.asList("a", "bb", "a", "bb")), "{bb=2, a=2}"),
                    Arguments.of(
                            new ArrayList<>(Arrays.asList("код", "код", "код", "bug")), "{код=3, bug=1}"),
                    Arguments.of(
                            new ArrayList<>(Arrays.asList("this", "and", "that", "and")),
                            "{that=1, and=2, this=1}")
            );
        }
    }
}
