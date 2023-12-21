package edu.project3;

import edu.project3.LogExecutors.LocalPathExecutor;
import edu.project3.LogExecutors.LogExecutor;
import edu.project3.LogExecutors.URLExecutor;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {

    public final Map<String, Integer> resources;
    public final Map<String, Integer> userAgent;
    public final Map<Integer, Integer> codes;
    public final Map<String, Integer> ipCount;

    public BigInteger requestCount;
    public BigInteger requestSize;
    public final LocalDate dateTo;
    public final LocalDate dateFrom;
    public final String format;
    public List<String> fileNames;

    public LogAnalyzer(LocalDate dateFrom, LocalDate dateTo, String format) {
        requestCount = BigInteger.valueOf(0);
        requestSize = BigInteger.valueOf(0);
        ipCount = new HashMap<>();
        codes = new HashMap<>();
        userAgent = new HashMap<>();
        resources = new HashMap<>();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;

        if (format != null && format.equals("markdown")) {
            this.format = "md";
        } else {
            this.format = format;
        }
        fileNames = new ArrayList<>();
    }

    public void linksAnalysis(List<String> urlList, List<String> localPath) {

        for (String url: urlList) {
            LogExecutor urlExecutor = new URLExecutor();
            urlExecutor.executeDataByPath(this, url);
        }

        for (String path: localPath) {
            LogExecutor localPathExecutor = new LocalPathExecutor();
            localPathExecutor.executeDataByPath(this, path);
        }
    }
}
