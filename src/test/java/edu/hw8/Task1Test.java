package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Quotes helper check")
    void testQuotesHelper() {
        boolean ok = true;

        List<String> stringList = new ArrayList<>(List.of("личности", "оскорбления", "глупый", "интеллект", "брат", "волк", "работа"));

        try {
            Thread serverThread = new Thread(() -> {
                Server server  = new Server();
                server.start();
            });
        serverThread.start();
        Thread.sleep(1000);

        List<Thread> requests = Stream.generate(() -> new Thread(() -> {
            Client.makeRequest(stringList.get(ThreadLocalRandom.current().nextInt(7)));

            Client.makeRequest(stringList.get(ThreadLocalRandom.current().nextInt(7)));
        })).limit(10).toList();

        for (var request : requests) {
            request.start();
            Thread.sleep(200);
        }

        for (var request : requests) {
            try {
                request.join();
            } catch (InterruptedException e) {
                ok = false;
            }
        }

        Client.stopClient();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            ok = false;
        }

        } catch (Exception e) {
            ok = false;
        }

        assertThat(ok).isEqualTo(true);
    }
}
