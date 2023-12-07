package edu.hw3;

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
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Checking atBash function")
    void testAtBash(String text, String writeAnswer) {
        assertThat(Task1.atBash(text)).isEqualTo(writeAnswer);
    }


    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("Hello world!", "Svool dliow!"),
                    Arguments.of("Any fool can write code that a computer can understand."
                                    + " Good programmers write code that humans can understand. - Martin Fowler",
                            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw."
                                    + " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. - Nzigrm Uldovi"),
                    Arguments.of("Проверка нелатинских букв, а так же символов!?@#",
                            "Проверка нелатинских букв, а так же символов!?@#")
            );
        }
    }
}
