package edu.hw9.Task2;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class DirectoriesSearcher extends RecursiveTask<List<Path>> {

    private static final int VALUE_OF_SEARCH_DEPTH = 1000;
    private final Path path;
    private final List<Path> result;

    public DirectoriesSearcher(Path newPath, List<Path> ans) {
        path = newPath;
        result = ans;
    }

    @Override
    protected List<Path> compute() {
        for (var currentFile : Objects.requireNonNull(path.toFile().listFiles())) {
            if (currentFile.isDirectory()) {
                if (Objects.requireNonNull(currentFile.listFiles()).length >= VALUE_OF_SEARCH_DEPTH) {
                    result.add(Path.of(currentFile.getAbsolutePath()));
                }

                DirectoriesSearcher directoriesSearcher =
                        new DirectoriesSearcher(Path.of(currentFile.getAbsolutePath()), result);

                directoriesSearcher.fork();
                directoriesSearcher.join();
            }
        }

        return result;
    }
}
