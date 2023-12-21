package edu.project3.LogExecutors;

import edu.project3.DataParser;
import edu.project3.LogAnalyzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class LocalPathExecutor implements LogExecutor {

    public LocalPathExecutor() {
    }

    private static String getDirectory(String path) {
        int lastSlash = path.lastIndexOf("/", (!path.contains("*")) ? (path.length()) : (path.indexOf("*")));

        if (lastSlash < path.indexOf("*")) {
            lastSlash = path.indexOf("*");
        }

        return path.substring(0, (lastSlash == -1) ? (path.length()) : (lastSlash));
    }

    private static String getName(String path) {
        if (path.charAt(path.length() - 1) == '/' || path.charAt(path.length() - 1) == '*') {
            return null;
        } else {
            return path.substring(path.lastIndexOf("/") + 1);
        }
    }

    @Override
    public void executeDataByPath(LogAnalyzer logAnalyzer, String path) {
        String directory = getDirectory(path);
        String name = getName(path);

        File file = new File(directory);

        checkFile(logAnalyzer, file, name);
    }

    private static void checkFile(LogAnalyzer logAnalyzer, File file, String name) {
        if (!file.isDirectory()) {
            if (file.getName().equals(name) || name == null) {

                logAnalyzer.fileNames.add(file.getName());

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

                    String log = bufferedReader.readLine();
                    DataParser.parseLogs(logAnalyzer, log);

                } catch (IOException | ParseException ignored) {
                }
            }
        } else {
            for (File newFile: Objects.requireNonNull(file.listFiles())) {
                checkFile(logAnalyzer, newFile, name);
            }
        }
    }
}
