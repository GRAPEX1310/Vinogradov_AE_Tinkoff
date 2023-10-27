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

public class Task5Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task5Test.TestArgumentsProvider.class)
    @DisplayName("Checking parseContacts function")
    void testParseContactsFunction(ArrayList<String> givenArray, String sortType, String writeAnswer) {
        assertThat(Task5.parseContacts(givenArray, sortType).toString()).isEqualTo(writeAnswer);
    }


    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(
                            new ArrayList<>(Arrays.asList("John Locke", "Thomas Aquinas",
                                    "David Hume", "Rene Descartes")),
                            "ASC", "[Thomas Aquinas, Rene Descartes, David Hume, John Locke]"),
                    Arguments.of(new ArrayList<>(Arrays.asList("Paul Erdos", "Leonhard Euler", "Carl Gauss")),
                            "DESC", "[Carl Gauss, Leonhard Euler, Paul Erdos]"),
                    Arguments.of(new ArrayList<>(Arrays.asList()),
                            "DESC", "[]"),
                    Arguments.of(null,
                            "DESC", "[]")
            );
        }
    }
}
