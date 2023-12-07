package edu.hw2;

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
    @DisplayName("Checking Constant function efficiency")
    void testConstantFunction(double[] values) {
        assertThat(new Task1.Constant(values[0]).evaluate()).isEqualTo(values[2]);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Checking Negate function efficiency")
    void testNegateFunction(double[] values) {
        assertThat(new Task1.Negate(new Task1.Constant(values[0])).evaluate()).isEqualTo(values[3]);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Checking Exponent function efficiency")
    void testExponentFunction(double[] values) {
        assertThat(new Task1.Exponent(
                new Task1.Constant(values[0]), values[1]).evaluate()
        ).isEqualTo(values[4]);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Checking Addition function efficiency")
    void testAdditionFunction(double[] values) {
        assertThat(new Task1.Addition(
                new Task1.Constant(values[0]),
                new Task1.Constant(values[1])
        ).evaluate()).isEqualTo(values[5]);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Checking Multiplication function efficiency")
    void testMultiplicationFunction(double[] values) {
        assertThat(new Task1.Multiplication(
                new Task1.Constant(values[0]),
                new Task1.Constant(values[1])
        ).evaluate()).isEqualTo(values[6]);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    // [first_value, second_value, constantAnswer, negateAnswer,
                    // exponentAnswer, additionAnswer, multiplicationAnswer]
                    Arguments.of((Object) new double[]{5, 4, 5, -5, 625, 9, 20}),
                    Arguments.of((Object) new double[]{-3, 2, -3, 3, 9, -1, -6}),
                    Arguments.of((Object) new double[]{2, -1, 2, -2, 0.5, 1, -2}),
                    Arguments.of((Object) new double[]{-4, -2, -4, 4, 0.0625, -6, 8})
            );
        }
    }
}
