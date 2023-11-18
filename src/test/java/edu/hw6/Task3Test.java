package edu.hw6;

import edu.hw6.Task3.Task3;
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

    @Test
    @DisplayName("Test AbstractFilter working")
    void testAbstractFilter() {
        Path dir = Paths.get("");

        DirectoryStream.Filter<Path> pathFilter1 = Task3.isWritable()
                .and(Task3.fileName("pom"))
                .and(Task3.largerThan(1))
                .and(Task3.lessThan(30000));

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, pathFilter1)) {
            List<Path> pathList = new ArrayList<>();
            directoryStream.forEach(pathList::add);

            assertThat(pathList.toArray().length).isEqualTo(1);
            assertThat(pathList.toArray()).contains(Paths.get("pom.xml"));
        } catch (IOException e) {
        }

        DirectoryStream.Filter<Path> pathFilter2 = Task3.isReadable()
                .and(Task3.isRegular())
                .and(Task3.globMatches("*.gitignore"));

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, pathFilter2)) {
            List<Path> pathList = new ArrayList<>();
            directoryStream.forEach(pathList::add);

            assertThat(pathList.toArray().length).isEqualTo(1);
            assertThat(pathList.toArray()).contains(Paths.get(".gitignore"));
        } catch (IOException e) {
        }
    }
}
