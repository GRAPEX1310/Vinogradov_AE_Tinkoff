package edu.hw9;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TreeSearchMultiThreadEngine {

    private TreeSearchMultiThreadEngine() {
    }

    private static List<File> validDirectories;
    private static List<File> validFiles;

    private static Predicate<File> predicate;
    private static int fileCounter;

    public static List<File> searchDirectories(Path startPosition, int fileCount) {
        validDirectories = new ArrayList<>();
        fileCounter = fileCount;

        DirectoryViewer directoryViewer = new DirectoryViewer(startPosition.toFile());

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.invoke(directoryViewer);
        forkJoinPool.close();

        return validDirectories;

    }

    public static List<File> searchFiles(Path startPosition, Predicate<File> filePredicate) {
        validFiles = new ArrayList<>();
        predicate = filePredicate;

        FileViewer fileViewer = new FileViewer(startPosition.toFile());

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.invoke(fileViewer);
        forkJoinPool.close();

        return validFiles;
    }

    private static final class FileViewer extends RecursiveAction {

        private final File file;

        private FileViewer(File newFile) {
            file = newFile;
        }

        @Override
        protected void compute() {
            if (file.isFile()) {
                if (predicate.test(file)) {
                    validFiles.add(file);
                }
            } else {
                List<File> dataInDirectory = Arrays.asList(Objects.requireNonNull(file.listFiles()));

                if (!dataInDirectory.isEmpty()) {
                    Stream<FileViewer> searcherStream = dataInDirectory.stream().map(FileViewer::new);

                    searcherStream.forEach(ForkJoinTask::fork);
                }
            }
        }
    }

    private static class DirectoryViewer extends RecursiveTask<Integer> {

        private final File file;

        private DirectoryViewer(File newFile) {
            file = newFile;
        }

        @Override
        protected Integer compute() {
            if (file.isDirectory()) {
                List<File> dataInDirectory = Arrays.asList(Objects.requireNonNull(file.listFiles()));

                if (dataInDirectory.isEmpty()) {
                    return 0;
                }

                int result = dataInDirectory.stream()
                        .map(DirectoryViewer::new)
                        .peek(ForkJoinTask::fork)
                        .map(ForkJoinTask::join)
                        .mapToInt(Integer::intValue)
                        .sum();

                if (result >= fileCounter) {
                    validDirectories.add(file);
                }

                return result;
            } else {
                return 1;
            }
        }
    }
}
