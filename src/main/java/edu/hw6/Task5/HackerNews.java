package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HackerNews {

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEMS_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final String EXTRA_CHARACTERS_DELETE = "[\\[\\]\"]";
    private static final String ERROR_MESSAGE = "Error during I/O operation";

    private static final int GOOD_CODE = 200;
    private static final Pattern TITLE_PATTERN = Pattern.compile("\"title\":\"(.*)\",\"type");
    private static final Logger LOGGER = LogManager.getLogger();

    private HackerNews() {

    }

    public static long[] hackerNewsTopStories() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(TOP_STORIES_URL)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == GOOD_CODE) {
                String[] responseArray = response.body()
                        .replaceAll(EXTRA_CHARACTERS_DELETE, "")
                        .split(",");

                long[] result = new long[responseArray.length];

                for (int i = 0; i < result.length; i++) {
                    result[i] = Long.parseLong(responseArray[i].trim());
                }
                return result;
            }

        } catch (IOException | InterruptedException exception) {
            LOGGER.error(ERROR_MESSAGE);
            return new long[0];
        }

        return new long[0];
    }

    public static String news(long id) {
        String itemUrl = String.format(ITEMS_URL, id);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(itemUrl)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == GOOD_CODE) {
                Matcher matcher = TITLE_PATTERN.matcher(response.body());

                if (matcher.find()) {
                    return matcher.group(1);
                }
            }

        } catch (IOException | InterruptedException e) {
            LOGGER.error(ERROR_MESSAGE);
            return "";
        }

        return "";
    }
}
