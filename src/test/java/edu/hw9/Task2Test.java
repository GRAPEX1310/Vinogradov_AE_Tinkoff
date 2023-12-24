package edu.hw9;

import edu.hw9.Task2.DirectoriesSearcher;
import edu.hw9.Task2.SpecialFileSearcher;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    public Task2Test() throws IOException {
    }

    private static Path testPath = Path.of("src/test/java/edu/hw9/dirs");
    private Path tempDirectory = Files.createTempDirectory(testPath, "new_dir");

    private List<String> extensions = List.of(".txt", ".doc", ".json");

    private void createTempDirectories(Path tempDirectory, int depth) throws IOException {
        if (depth < 3) {
            Path temp = tempDirectory;
            for (int i = 0; i < 2000; ++i) {
                if (ThreadLocalRandom.current().nextInt(1000) == 999) {
                    temp = Files.createTempDirectory(temp, "dir_" + i);
                    depth++;
                    createTempDirectories(temp, depth);
                } else {
                    File.createTempFile("00_" + i, extensions.get(ThreadLocalRandom.current()
                            .nextInt(3)), temp.toFile()).deleteOnExit();
                }
            }
        }
    }

    @Test
    @DisplayName("Check DirectorySearcher work")
    void testDirectorySearcher() throws IOException {
        createTempDirectories(tempDirectory, 0);
        List<Path> result = new ArrayList<>();

        DirectoriesSearcher directoriesSearcher = new DirectoriesSearcher(testPath, result);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(directoriesSearcher);

        assertThat(result).isNotEmpty();
        forkJoinPool.close();
    }

    @Test
    @DisplayName("Check SpecialFilesSearcher work")
    void testSpecialFilesSearcher() throws IOException {
        createTempDirectories(tempDirectory, 2);

        List<Path> result = new ArrayList<>();
        int size = 512;

        String extension = "txt";
        SpecialFileSearcher specialFileSearcher = new SpecialFileSearcher(size, extension, result, testPath);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(specialFileSearcher);

        assertThat(result).isNotEmpty();
        forkJoinPool.close();
    }
}
