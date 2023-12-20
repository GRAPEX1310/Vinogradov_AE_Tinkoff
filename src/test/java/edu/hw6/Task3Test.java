package edu.hw6;

import edu.hw6.Task3.CompositeFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    private static final Path dir = Paths.get("");

    @Test
    @DisplayName("First test AbstractFilter working")
    void firstTestAbstractFilter() {
        DirectoryStream.Filter<Path> pathFilter1 = CompositeFilter.IS_WRITABLE
                .and(CompositeFilter.fileName("pom.xml"))
                .and(CompositeFilter.largerThan(1))
                .and(CompositeFilter.lessThan(30000));

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, pathFilter1)) {
            List<Path> pathList = new ArrayList<>();
            directoryStream.forEach(pathList::add);

            assertThat(pathList.toArray().length).isEqualTo(1);
            assertThat(pathList.toArray()).contains(Paths.get("pom.xml"));
        } catch (IOException ignored) {
        }
    }

    @Test
    @DisplayName("Second test AbstractFilter working")
    void secondTestAbstractFilter() {
        DirectoryStream.Filter<Path> pathFilter2 = CompositeFilter.IS_READABLE
                .and(CompositeFilter.IS_REGULAR)
                .and(CompositeFilter.globMatches(".gitignore"));

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, pathFilter2)) {
            List<Path> pathList = new ArrayList<>();
            directoryStream.forEach(pathList::add);

            assertThat(pathList.toArray().length).isEqualTo(1);
            assertThat(pathList.toArray()).contains(Paths.get(".gitignore"));
        } catch (IOException ignored) {
        }
    }
}
