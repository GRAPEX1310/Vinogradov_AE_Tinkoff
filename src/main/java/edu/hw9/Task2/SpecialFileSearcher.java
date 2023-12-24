package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialFileSearcher extends RecursiveTask<List<Path>> {

    private static final int KILOBYTE_HALF = 512;
    private final long fileSize;
    private final String extension;
    private final List<Path> result;
    private final Path path;

    public SpecialFileSearcher(long size, String extension, List<Path> ans, Path path) {
        fileSize = size;
        this.extension = extension;
        result = ans;
        this.path = path;
    }

    @Override
    protected List<Path> compute() {
        for (var currentFile : Objects.requireNonNull(path.toFile().listFiles())) {
            if (currentFile.isDirectory()) {
                SpecialFileSearcher searcher =
                        new SpecialFileSearcher(fileSize, extension, result, Path.of(currentFile.getAbsolutePath()));
                searcher.fork();
                searcher.join();
            } else {
                try {
                    if (fileSize - KILOBYTE_HALF <= Files.size(Path.of(currentFile.getAbsolutePath()))
                            && Files.size(Path.of(currentFile.getAbsolutePath())) <= fileSize + KILOBYTE_HALF) {
                        String fileName = currentFile.getName();
                        Matcher matcher = Pattern.compile("\\w+." + extension).matcher(fileName);
                        if (matcher.find()) {
                            result.add(Path.of(currentFile.getAbsolutePath()));
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }
}
