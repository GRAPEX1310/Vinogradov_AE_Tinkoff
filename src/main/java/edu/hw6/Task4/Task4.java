package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task4 {

    private Task4() {

    }

    private static final String ERROR_MESSAGE = "File not found";
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("NestedTryDepth")
    public static void outputStreamChain(String text) {
        File file = new File("src/main/resources/Result.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            try (CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32())) {
                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream)) {
                    try (OutputStreamWriter outputStreamWriter =
                                 new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8)) {
                        try (PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
                            printWriter.write(text);
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(ERROR_MESSAGE);
        }
    }
}
