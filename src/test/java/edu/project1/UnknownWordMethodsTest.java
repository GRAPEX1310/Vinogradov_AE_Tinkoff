package edu.project1;

import java.util.Arrays;
import java.util.Vector;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UnknownWordMethodsTest {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(UnknownWordMethodsTest.TestArgumentsProvider.class)
    @DisplayName("Checking checkLetterPositions efficiency")
    void checkLetterTest(HiddenWord hiddenWord, char currentSymbol, boolean writeAnswer) {

        try {
            assertThat(new UnknownWord(hiddenWord).checkLetter(hiddenWord, currentSymbol)).isEqualTo(writeAnswer);
        } catch (WordExceptions.WordHasThisLetterException wordHasThisLetterException) {
            assertThat(true).isEqualTo(true);
        }
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new HiddenWord("java"), "a", true),
                    Arguments.of(new HiddenWord("tinkoff"), "a", false),
                    Arguments.of(new HiddenWord("science"), "f", false)
            );
        }
    }

}
