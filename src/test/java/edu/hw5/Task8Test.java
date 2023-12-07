package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    //reg1

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task8Test.TestArgumentsProvider1.class)
    @DisplayName("Checking oddLengthValidation function efficiency")
    void testOddLengthValidation(String string, boolean result) {
        assertThat(Task8.oddLengthValidation(string)).isEqualTo(result);
    }

    //reg2

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task8Test.TestArgumentsProvider2.class)
    @DisplayName("Checking zeroOddAndOneEvenValidation function efficiency")
    void testZeroOddAndOneEvenValidation(String string, boolean result) {
        assertThat(Task8.zeroOddAndOneEvenValidation(string)).isEqualTo(result);
    }

    //reg3
    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task8Test.TestArgumentsProvider3.class)
    @DisplayName("Checking zeroDividedBy3Validation function efficiency")
    void testZeroDividedBy3Validation(String string, boolean result) {
        assertThat(Task8.zeroDividedBy3Validation(string)).isEqualTo(result);
    }

    //reg4
    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task8Test.TestArgumentsProvider4.class)
    @DisplayName("Checking ex11Ex111Validation function efficiency")
    void testEx11Ex111Validation(String string, boolean result) {
        assertThat(Task8.ex11Ex111Validation(string)).isEqualTo(result);
    }

    //reg5

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task8Test.TestArgumentsProvider5.class)
    @DisplayName("Checking oddOneValidation function efficiency")
    void testOddOneValidation(String string, boolean result) {
        assertThat(Task8.oddOneValidation(string)).isEqualTo(result);
    }

    //reg6
    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task8Test.TestArgumentsProvider6.class)
    @DisplayName("Checking moreThan2ZerosAndLessThan2OnesValidation function efficiency")
    void testMoreThan2ZerosAndLessThan2OnesValidation(String string, boolean result) {
        assertThat(Task8.moreThan2ZerosAndLessThan2OnesValidation(string)).isEqualTo(result);
    }

    //reg7
    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task8Test.TestArgumentsProvider7.class)
    @DisplayName("Checking withoutConsecutive1Validation function efficiency")
    void testWithoutConsecutive1Validation(String string, boolean result) {
        assertThat(Task8.withoutConsecutive1Validation(string)).isEqualTo(result);
    }

    //provider1
    static class TestArgumentsProvider1 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("10101", true),
                    Arguments.of("1", true),
                    Arguments.of("123", false),
                    Arguments.of("11", false),
                    Arguments.of(null, false)
            );
        }
    }

    //provider2
    static class TestArgumentsProvider2 implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("0", true),
                    Arguments.of("10", false),
                    Arguments.of("123", false),
                    Arguments.of("111", false),
                    Arguments.of("1111", true),
                    Arguments.of("00", false),
                    Arguments.of(null, false)
            );
        }
    }

    //provider3
    static class TestArgumentsProvider3 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("01100010110", true),
                    Arguments.of("000", true),
                    Arguments.of("1001", false),
                    Arguments.of("011", false),
                    Arguments.of("123", false),
                    Arguments.of(null, false)
            );
        }
    }

    //provider4
    static class TestArgumentsProvider4 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("01100010110", true),
                    Arguments.of("000", true),
                    Arguments.of("011", true),
                    Arguments.of("123", false),
                    Arguments.of("111", false),
                    Arguments.of("11", false),
                    Arguments.of(null, false)
            );
        }
    }

    //provider5
    static class TestArgumentsProvider5 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("11111111", true),
                    Arguments.of("101", true),
                    Arguments.of("01100010110", false),
                    Arguments.of("123", false),
                    Arguments.of("1", true),
                    Arguments.of("", false),
                    Arguments.of(null, false)
            );
        }
    }

    //provider6
    static class TestArgumentsProvider6 implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("0100", true),
                    Arguments.of("01", false),
                    Arguments.of("123", false),
                    Arguments.of("101", false),
                    Arguments.of("000001", true),
                    Arguments.of("1000000", true),
                    Arguments.of(null, false)
            );
        }
    }

    //provider7
    static class TestArgumentsProvider7 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("0100", true),
                    Arguments.of("01100", false),
                    Arguments.of("123", false),
                    Arguments.of("101", true),
                    Arguments.of("01000101", true),
                    Arguments.of("100011010001", false),
                    Arguments.of(null, false)
            );
        }
    }
}
