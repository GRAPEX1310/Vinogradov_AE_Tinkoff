package edu.project1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UnkownWordMethodsTest {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(UnkownWordMethodsTest.TestArgumentsProvider.class)
    @DisplayName("Checking checkLetterPositions efficiency")
    void checkLetterTest() {
        assertThat().isEqualTo();
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of('f', "tinkoff", new Vector<Integer>(Arrays.asList(5, 6))),
                    Arguments.of('a', "java", new Vector<Integer>(Arrays.asList(1, 3))),
                    Arguments.of('q', "computer", new Vector<Integer>()),
                    Arguments.of('s', "science", new Vector<Integer>(Arrays.asList(0)))
            );
        }
    }

}
