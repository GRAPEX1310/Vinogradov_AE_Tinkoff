package edu.project3.LogExecutors;

import edu.project3.DataParser;
import edu.project3.LogAnalyzer;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class URLExecutor implements LogExecutor {

    public URLExecutor() {
    }

    private static Stream<String> makeRequest(HttpClient client, String path)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
    }

    @Override
    public void executeDataByPath(LogAnalyzer logAnalyzer, String urlPath) {

        AtomicLong requestCount = new AtomicLong(0);
        AtomicLong requestSize = new AtomicLong(0);

        logAnalyzer.fileNames.add(urlPath.substring(urlPath.lastIndexOf("/")));

        try {
            HttpClient client = HttpClient.newHttpClient();
            Stream<String> logStream = makeRequest(client, urlPath);

            logStream.forEach(log -> {
                try {
                    DataParser.parseLogs(logAnalyzer, log);
                } catch (ParseException ignored) {
                }
            });
        } catch (IOException | InterruptedException ignored) {
        }

//        logAnalyzer.requestCount = logAnalyzer.requestCount.add(BigInteger.valueOf(requestCount.get()));
//        logAnalyzer.requestSize = logAnalyzer.requestSize.add(BigInteger.valueOf(requestSize.get()));
    }
}
