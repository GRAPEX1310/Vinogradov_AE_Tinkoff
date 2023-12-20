package edu.hw6.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloneFile {

    private static final Pattern FILE_NAME_PATTERN = Pattern.compile("^([A-z0-9._-]+)(\\.[A-z0-9]+)$");
    private static final String FILE_COPY = " - копия";

    private CloneFile() {

    }

    public static void cloneFile(Path path) {
        File file = path.toFile();

        try {
            if (!file.createNewFile()) {
                String fileName = path.getFileName().toString();
                Matcher matcher = FILE_NAME_PATTERN.matcher(fileName);
                StringBuilder resultFileName = new StringBuilder();

                if (matcher.find()) {
                    resultFileName.append(matcher.group(1)).append(FILE_COPY);

                    if (!new File(resultFileName + matcher.group(2)).createNewFile()) {
                        int fileNumber = 1;

                        while (!new File(resultFileName + "(" + fileNumber + ")"
                                + matcher.group(2)).createNewFile()) {
                            fileNumber++;
                        }
                    }
                } else {
                    resultFileName.append(fileName).append(FILE_COPY);
                    if (!new File(resultFileName.toString()).createNewFile()) {
                        int fileNumber = 1;

                        while (!new File(resultFileName + "(" + fileNumber + ")").createNewFile()) {
                            fileNumber++;
                        }
                    }
                }
            }
        } catch (IOException ignored) {
        }
    }

    public static void createFile(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
