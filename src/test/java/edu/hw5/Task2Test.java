package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task2Test.TestArgumentsProvider1.class)
    @DisplayName("Checking scaryFridaySearch function efficiency")
    void testScaryFridaySearch(int year, String result) {
        assertThat(Task2.scaryFridaySearch(year).toString()).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(Task2Test.TestArgumentsProvider2.class)
    @DisplayName("Checking nearestScaryDay function efficiency")
    void testNearestScaryDay(LocalDate date, LocalDate result) {
        assertThat(Task2.nearestScaryDay(date)).isEqualTo(result);
    }

    static class TestArgumentsProvider1 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(2023, "[2023-01-13, 2023-10-13]"),
                    Arguments.of(2004, "[2004-02-13, 2004-08-13]"),
                    Arguments.of(2020, "[2020-03-13, 2020-11-13]")
            );
        }
    }

    static class TestArgumentsProvider2 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13)),
                    Arguments.of(LocalDate.of(1925, 2, 13), LocalDate.of(1925, 3, 13)),
                    Arguments.of(LocalDate.of(3412, 11, 13), LocalDate.of(3413, 8, 13))
            );
        }
    }
}
