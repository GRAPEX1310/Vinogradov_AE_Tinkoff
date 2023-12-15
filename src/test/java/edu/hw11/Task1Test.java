package edu.hw11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("ByteBuddy override toString test")
    void testToStringOverride() {

        boolean correct = false;
        String result = "";
        try {
            correct = true;
            result = Task1.overrideToStringMethod();
        } catch (Exception e) {
            correct = false;
        }

        assertThat(correct).isTrue();

        assertThat(result).isEqualTo("Hello, ByteBuddy!");
    }
}
