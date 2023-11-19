package edu.project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalPathExecutor {

    private LocalPathExecutor() {

    }

    private static final String TIME_FORMAT = "dd/MMMM/yyyy:hh:mm:ss";

    private static final Pattern LOG_PATTERN = Pattern.compile(
            "([0-9.]+) - - \\[(\\S+) \\S+\\] \"GET .*/(.+) .*\" (\\d+) (\\d+) \".+\" \"([0-9A-Za-z/.-]+) .+\"");

    private enum GROUPS {
        UNKNOWN,
        IP,
        TIME,
        RESOURCE,
        CODE,
        SIZE,
        AGENT

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

    private static void checkLog(LogAnalyzer logAnalyzer, String log) throws ParseException {
        Matcher logMather = LOG_PATTERN.matcher(log);
        if (logMather.find()) {

            Date date = new SimpleDateFormat(TIME_FORMAT, Locale.US)
                    .parse(logMather.group(LocalPathExecutor.GROUPS.TIME.ordinal()));

            LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());

            if ((logAnalyzer.dateFrom == null || !logAnalyzer.dateFrom.isAfter(localDate))
                    && (logAnalyzer.dateTo == null || logAnalyzer.dateTo.isAfter(localDate))) {

                logAnalyzer.ipCount.merge(logMather.group(LocalPathExecutor.GROUPS.IP.ordinal()),
                        1, Integer::sum
                );
                logAnalyzer.codes.merge(Integer.valueOf(logMather.group(
                                LocalPathExecutor.GROUPS.CODE.ordinal())),
                        1, Integer::sum
                );
                logAnalyzer.userAgent.merge(logMather.group(LocalPathExecutor.GROUPS.AGENT.ordinal()),
                        1, Integer::sum);
                logAnalyzer.resources.merge(logMather.group(LocalPathExecutor.GROUPS.RESOURCE.ordinal()),
                        1, Integer::sum
                );
                logAnalyzer.requestCount = logAnalyzer.requestCount.add(BigInteger.ONE);
                logAnalyzer.requestSize = logAnalyzer.requestSize.add(BigInteger.valueOf(Long.parseLong(
                        logMather.group(GROUPS.SIZE.ordinal()))));
            }
        }
    }

    public static void checkPath(LogAnalyzer logAnalyzer, String path) {
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

                    checkLog(logAnalyzer, log);
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
