package edu.hw3;

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
    @DisplayName("Checking convertToRoman function")
    void testConvertToRomanFunction(int arabicNumber, String romanNumber) {
        assertThat(Task4.convertToRoman(arabicNumber)).isEqualTo(romanNumber);
    }


    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(2, "II"),
                    Arguments.of(14, "XIV"),
                    Arguments.of(19, "XIX"),
                    Arguments.of(41, "XLI"),
                    Arguments.of(44, "XLIV"),
                    Arguments.of(49, "XLIX"),
                    Arguments.of(88, "LXXXVIII"),
                    Arguments.of(99, "XCIX"),
                    Arguments.of(101, "CI"),
                    Arguments.of(999, "CMXCIX"),
                    Arguments.of(1111, "MCXI"),
                    Arguments.of(2517, "MMDXVII"),
                    Arguments.of(3999, "MMMCMXCIX")
            );
        }
    }
}
