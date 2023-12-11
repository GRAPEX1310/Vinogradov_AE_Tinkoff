package edu.hw6;

import edu.hw6.Task2.CloneFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    private static final String FIRST_FILE = "file.txt";
    private static final String SECOND_FILE = "file";

    @Test
    @DisplayName("Test CloneFile working")
    void testCloneFile() {
        clear();

        assertThat(new File(FIRST_FILE).exists()).isEqualTo(false);

        CloneFile.createFile(Paths.get(FIRST_FILE));
        assertThat(new File(FIRST_FILE).exists()).isEqualTo(true);

        CloneFile.cloneFile(Paths.get(FIRST_FILE));
        assertThat(new File("file - копия.txt").exists()).isEqualTo(true);

        CloneFile.cloneFile(Paths.get(FIRST_FILE));
        assertThat(new File("file - копия(1).txt").exists()).isEqualTo(true);

        CloneFile.cloneFile(Paths.get(FIRST_FILE));
        assertThat(new File("file - копия(2).txt").exists()).isEqualTo(true);

        assertThat(new File(SECOND_FILE).exists()).isEqualTo(false);

        CloneFile.createFile(Paths.get(SECOND_FILE));
        assertThat(new File(SECOND_FILE).exists()).isEqualTo(true);

        CloneFile.cloneFile(Paths.get(SECOND_FILE));
        assertThat(new File("file - копия").exists()).isEqualTo(true);

        CloneFile.cloneFile(Paths.get(SECOND_FILE));
        assertThat(new File("file - копия(1)").exists()).isEqualTo(true);

        CloneFile.cloneFile(Paths.get(SECOND_FILE));
        assertThat(new File("file - копия(2)").exists()).isEqualTo(true);

        clear();
    }

    @BeforeEach
    public void clear() {
        File file = new File("file.txt");
        file.delete();
        file = new File("file - копия.txt");
        file.delete();
        file = new File("file - копия(1).txt");
        file.delete();
        file = new File("file - копия(2).txt");
        file.delete();
        file = new File("file");
        file.delete();
        file = new File("file - копия");
        file.delete();
        file = new File("file - копия(1)");
        file.delete();
        file = new File("file - копия(2)");
        file.delete();
    }
}