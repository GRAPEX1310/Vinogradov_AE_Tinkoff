package edu.hw6.Task5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HackerNews {

    private static final String NEWS_FILENAME = "news.json";
    private static final String TOP_STORIES_FILENAME = "top_stories.json";
    private static final String ERROR_MESSAGE = "Error during I/O operation";

    private static final Pattern TITLE_PATTERN = Pattern.compile("\"title\":\"(.*)\",\"type");

    private static final Logger LOGGER = LogManager.getLogger();

    private HackerNews() {

    }

    public static long[] hackerNewsTopStories() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            createRequest(client, "https://hacker-news.firebaseio.com/v0/topstories.json", TOP_STORIES_FILENAME);

            File file = new File(TOP_STORIES_FILENAME);
            String id = null;

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                id = bufferedReader.readLine();
            } catch (IOException ioException) {
                LOGGER.error(ERROR_MESSAGE);
            }

            boolean correctDeleting = file.delete();

            if (id == null) {
                throw new IOException();
            } else {
                String[] storiesNumber = id.substring(1, id.length() - 1).split(",");

                return ArrayUtils.toPrimitive(Arrays.stream(storiesNumber).map(Long::parseLong).toArray(Long[]::new));
            }

        } catch (IOException | InterruptedException exception) {
            LOGGER.error(ERROR_MESSAGE);
            return new long[0];
        }
    }

    public static String news(long id) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            createRequest(client, "https://hacker-news.firebaseio.com/v0/item/"+ id +".json", NEWS_FILENAME);

            File file = new File(NEWS_FILENAME);
            String title = null;

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                title = bufferedReader.readLine();
            } catch (IOException ioException) {
                LOGGER.error(ERROR_MESSAGE);
            }

            boolean correctDeleting = file.delete();

            if (title == null) {
                throw new IOException();
            }

            Matcher matcher = TITLE_PATTERN.matcher(title);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                throw new IOException();
            }

        } catch (IOException | InterruptedException exception) {
            LOGGER.error(ERROR_MESSAGE);
            return null;
        }
    }

    private static void createRequest(HttpClient client, String path, String fileName)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(path)).GET().build();

        client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(fileName)));
    }
}
