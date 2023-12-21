package edu.project3.PrintStatistic;

import edu.project3.LogAnalyzer;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class MarkDownAndAdocOutput implements StatisticOutput {

    private final static String SEPARATOR = "|:---------------------:|--------------:|";
    private final static String SPECIFIC_OUTPUT_FORMAT = "|%-23s|%-15s|";
    private final static String COUNT_STRING = "Number";
    private final static String DATE_FORMAT = "dd.MM.yyyy";
    private final static int SPECIFIC_FIRST_FIELD_WIDTH = 23;
    private final static int SPECIFIC_SECOND_FIELD_WIDTH = 15;
    private final static String SPECIFIC_CODES_OUTPUT_FORMAT = "| %s |%-23s|%-15s|";
    private final static String CODES_SEPARATOR = "|:----:|:---------------------:|--------------:|";
    private final static String FILENAME = "Log_Statistic.";
    private final static String LINE_SWITCH = System.lineSeparator();
    private final static Map<Integer, String> CODE_NAMES = Map.ofEntries(
            Map.entry(200, "OK"),
            Map.entry(404, "Not Found"),
            Map.entry(500, "Internal Server Error"),
            Map.entry(201, "Created"),
            Map.entry(204, "No Content"),
            Map.entry(304, "Not Modified"),
            Map.entry(410, "Gone"),
            Map.entry(400, "Bad Request"),
            Map.entry(423, "Locked"),
            Map.entry(416, "Range Not Satisfiable"),
            Map.entry(206, "Partial Content")
    );

    private final String fileFormat;
    private final LogAnalyzer logAnalyzer;
    private final StringBuilder result;

    public MarkDownAndAdocOutput(LogAnalyzer logAnalyzer) {
        this.logAnalyzer = logAnalyzer;
        this.fileFormat = logAnalyzer.format;
        this.result = new StringBuilder();
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    @Override
    public void printData() {
        logAnalyzer.fileNames.sort(Comparator.naturalOrder());
        printMetric();
        printResources();
        printAgent();
        printCodes();
        printIP();
        System.out.println(result);

        if (fileFormat != null) {
            printToFile();
        }
    }

    @Override
    public String getResultString() {
        return result.toString();
    }

    @Override
    public void printToFile() {
        try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(
                Paths.get(FILENAME + fileFormat),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING))) {

            outputStream.write(result.toString().getBytes());
        } catch (IOException ignored) {
        }
    }

    private void printMetric() {
        result.append(LINE_SWITCH);
        result.append("### General Information").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Metric"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, "Value"))).append(LINE_SWITCH);
        result.append(SEPARATOR).append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "File(s)"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH,
                        (logAnalyzer.fileNames.isEmpty())
                                ? ("-") : (logAnalyzer.fileNames.get(0))))).append(LINE_SWITCH);
        for (int nameIndex = 1; nameIndex < logAnalyzer.fileNames.size(); nameIndex++) {
            result.append(String.format(SPECIFIC_OUTPUT_FORMAT, "",
                    centerString(SPECIFIC_SECOND_FIELD_WIDTH,
                            logAnalyzer.fileNames.get(nameIndex)))).append(LINE_SWITCH);
        }
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Starting date"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, (logAnalyzer.dateFrom == null)
                        ? ("-") : (DateTimeFormatter.ofPattern(DATE_FORMAT).format(logAnalyzer.dateFrom)))))
                .append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Final date"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, (logAnalyzer.dateTo == null) ? ("-")
                        : (DateTimeFormatter.ofPattern(DATE_FORMAT).format(logAnalyzer.dateTo))))).append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Number of requests"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, logAnalyzer.requestCount.toString()))).append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Average request size"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH,
                        (Objects.equals(logAnalyzer.requestCount, BigInteger.ZERO)) ? ("-")
                                : (logAnalyzer.requestSize.divide(logAnalyzer.requestCount)) + "b")))
                .append(LINE_SWITCH);
    }

    private void printResources() {
        result.append(LINE_SWITCH);
        result.append("### Requested resources").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Resource"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, COUNT_STRING))).append(LINE_SWITCH);
        result.append(SEPARATOR).append(LINE_SWITCH);
        for (var resource: logAnalyzer.resources.entrySet()) {
            result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                    centerString(SPECIFIC_FIRST_FIELD_WIDTH, "'/" + resource.getKey() + "'"),
                    centerString(SPECIFIC_SECOND_FIELD_WIDTH, resource.getValue().toString()))).append(LINE_SWITCH);
        }
    }

    private void printCodes() {
        result.append(LINE_SWITCH);
        result.append("### Response codes").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_CODES_OUTPUT_FORMAT, "Code",
                centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Name"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, COUNT_STRING))).append(LINE_SWITCH);
        result.append(CODES_SEPARATOR).append(LINE_SWITCH);
        for (var code: logAnalyzer.codes.entrySet()) {
            result.append(String.format(SPECIFIC_CODES_OUTPUT_FORMAT, code.getKey().toString(),
                    centerString(SPECIFIC_FIRST_FIELD_WIDTH,
                            (CODE_NAMES.get(code.getKey()) == null) ? ("?") : (CODE_NAMES.get(code.getKey()))),
                    centerString(SPECIFIC_SECOND_FIELD_WIDTH, code.getValue().toString()))).append(LINE_SWITCH);
        }
    }

    private void printIP() {
        result.append(LINE_SWITCH);
        result.append("### IP addresses of requests").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "IP"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, COUNT_STRING))).append(LINE_SWITCH);
        result.append(SEPARATOR).append(LINE_SWITCH);
        for (var ip: logAnalyzer.ipCount.entrySet()) {
            result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                    centerString(SPECIFIC_FIRST_FIELD_WIDTH, ip.getKey()),
                    centerString(SPECIFIC_SECOND_FIELD_WIDTH, ip.getValue().toString()))).append(LINE_SWITCH);
        }
    }

    private void printAgent() {
        result.append(LINE_SWITCH);
        result.append("### User-Agent").append(LINE_SWITCH);
        result.append(LINE_SWITCH);
        result.append(String.format(SPECIFIC_OUTPUT_FORMAT, centerString(SPECIFIC_FIRST_FIELD_WIDTH, "Agent name"),
                centerString(SPECIFIC_SECOND_FIELD_WIDTH, COUNT_STRING))).append(LINE_SWITCH);
        result.append(SEPARATOR).append(LINE_SWITCH);
        for (var name: logAnalyzer.userAgent.entrySet()) {
            result.append(String.format(SPECIFIC_OUTPUT_FORMAT,
                    centerString(SPECIFIC_FIRST_FIELD_WIDTH, name.getKey()),
                    centerString(SPECIFIC_SECOND_FIELD_WIDTH, name.getValue().toString()))).append(LINE_SWITCH);
        }
    }

    private String centerString(int width, String s) {
        return String.format("%-" + width  + "s",
                String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
}
