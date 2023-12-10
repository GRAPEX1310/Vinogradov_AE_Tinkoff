package edu.hw9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import org.apache.commons.io.FilenameUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Check directory search")
    void testDirectorySearch() {
        List<File> files = TreeSearchMultiThreadEngine.searchDirectories(Paths.get("src"), 3);

        assertThat(!files.isEmpty()).isTrue();

        for (int i = 0; i < files.size(); i++) {
            for (int j = i + 1; j < files.size(); j++) {
                assertThat(files.get(i).equals(files.get(j))).isFalse();
            }
        }
    }

    @Test
    @DisplayName("Check files search")
    void testFileSearch() {

        Predicate<File> predicate = new Predicate<File>() {
            @Override
            public boolean test(File file) {
                try {
                    return Files.size(file.toPath()) >= 2500 && FilenameUtils.getExtension(file.getName()).equals("java");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        List<File> files = TreeSearchMultiThreadEngine.searchFiles(Paths.get("src"), predicate);

        assertThat(!files.isEmpty()).isTrue();

        for (var file : files) {
            assertThat(predicate.test(file)).isTrue();
        }
    }
}
