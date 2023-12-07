package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task6Test.TestArgumentsProvider.class)
    @DisplayName("Checking substringSearch function efficiency")
    void testSubstringSearch(String text, String string, boolean result) {
        assertThat(Task6.substringSearch(text, string)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("abcbacbaabcbacbbacaaa", "aaaaa", false),
                    Arguments.of("aaa0", "aa1", false),
                    Arguments.of("achfdbaabgabcaabg", "abc", true),
                    Arguments.of("!dude123", "!dude123", true),
                    Arguments.of(null, null, false)
            );
        }
    }
}
