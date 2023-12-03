package edu.hw8.Task1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuoteRequest implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ERROR = "Problems raise error!";

    private static final Map<String, String> GREAT_QUOTES = Map.ofEntries(
            Map.entry("личности", "Не переходи на личности там, где их нет"),
            Map.entry("оскорбления", "Если твои противники перешли на личные оскорбления,"
                    + " будь уверена — твоя победа не за горами"),
            Map.entry("глупый", "А я тебе говорил, что ты глупый?"
                    + " Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."),
            Map.entry("интеллект", "Чем ниже интеллект, тем громче оскорбления"),
            Map.entry("брат", "Не брат ты мне..."),
            Map.entry("волк", "В этой жизни ты либо волк, либо не волк"),
            Map.entry("работа", "Работа не волк, работа - это ворк")
    );

    private final Socket clientSocket;
    private final String message;

    public QuoteRequest(Socket socket, String message) {
        this.clientSocket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        try (PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream())) {
            printWriter.println(GREAT_QUOTES.get(message));
        } catch (IOException exception) {
            LOGGER.error(ERROR);
        }
    }
}
