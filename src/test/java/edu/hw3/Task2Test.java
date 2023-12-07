package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task2Test.TestArgumentsProvider.class)
    @DisplayName("Checking clusterize function")
    void testClusterizeFunction(String text, String writeAnswer) {
        assertThat(Task2.clusterize(text).toString()).isEqualTo(writeAnswer);
    }


    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("()()()", "[(), (), ()]"),
                    Arguments.of("((()))", "[((()))]"),
                    Arguments.of("((())())(()(()()))", "[((())()), (()(()()))]"),
                    Arguments.of("((()))(())()()(()())", "[((())), (()), (), (), (()())]")
            );
        }
    }
}
