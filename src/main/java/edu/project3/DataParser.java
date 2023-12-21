package edu.project3;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    private DataParser() {
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

    public static void parseLogs(LogAnalyzer logAnalyzer, String log) throws ParseException {
        Matcher logMather = LOG_PATTERN.matcher(log);
        if (logMather.find()) {

            Date date = new SimpleDateFormat(TIME_FORMAT, Locale.US)
                    .parse(logMather.group(GROUPS.TIME.ordinal()));

            LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());

            if ((logAnalyzer.dateFrom == null || !logAnalyzer.dateFrom.isAfter(localDate))
                    && (logAnalyzer.dateTo == null || logAnalyzer.dateTo.isAfter(localDate)
                    || logAnalyzer.dateTo.equals(localDate))) {

                logAnalyzer.ipCount.merge(logMather.group(GROUPS.IP.ordinal()),
                        1, Integer::sum
                );
                logAnalyzer.codes.merge(Integer.valueOf(logMather.group(
                                GROUPS.CODE.ordinal())),
                        1, Integer::sum
                );
                logAnalyzer.userAgent.merge(logMather.group(GROUPS.AGENT.ordinal()),
                        1, Integer::sum);
                logAnalyzer.resources.merge(logMather.group(GROUPS.RESOURCE.ordinal()),
                        1, Integer::sum
                );
                logAnalyzer.requestCount = logAnalyzer.requestCount.add(BigInteger.ONE);
                logAnalyzer.requestSize = logAnalyzer.requestSize.add(BigInteger.valueOf(Long.parseLong(
                        logMather.group(GROUPS.SIZE.ordinal()))));
            }
        }
    }
}
