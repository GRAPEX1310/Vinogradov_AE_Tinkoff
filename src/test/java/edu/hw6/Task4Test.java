package edu.hw6;

import edu.hw6.Task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    private static final String REFERENCE = "Programming is learned by writing programs. ― Brian Kernighan";

    @Test
    @DisplayName("Test outputStreamChain working")
    void testOutputStreamChain() {

        Task4.outputStreamChain(REFERENCE);

        File file = new File("src/main/java/edu/hw6/Task4/Result.txt");
        assertThat(file.exists()).isEqualTo(true);

        String answer = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            answer = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(answer).isEqualTo(REFERENCE);

        file.delete();
    }
}
