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

    public static void outputStreamChain(String text) {
        File file = new File("src/main/java/edu/hw6/Task4/Result.txt");
        try (PrintWriter printWriter = new PrintWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new CheckedOutputStream(
                                        new FileOutputStream(file), new Adler32())), StandardCharsets.UTF_8))) {

            printWriter.write(text);
        } catch (IOException e) {
            LOGGER.error(ERROR_MESSAGE);
        }
    }
}
