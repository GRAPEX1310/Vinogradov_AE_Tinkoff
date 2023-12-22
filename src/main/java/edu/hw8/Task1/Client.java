package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {

    private Client() {
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int PORT = 4004;
    private static final String STOP = "/stop";
    private static final String ERROR = "Problems with request!";

    private static String lastServerAnswer = null;

    public static void makeRequest(String message) {
        try (Socket socket = new Socket("127.0.0.1", PORT)) {
            try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                try (BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {

                    printWriter.println(message);
                    printWriter.flush();
                    lastServerAnswer = null;

                    if (!message.equals(STOP)) {
                        String currentMessage = bufferedReader.readLine();
                        LOGGER.info(currentMessage);
                        lastServerAnswer = currentMessage;
                    }
                }
            }
        } catch (IOException exception) {
            LOGGER.error(ERROR);
        }
    }

    public static void stopClient() {
        makeRequest(STOP);
    }

    public static String getLastServerAnswer() {
        return lastServerAnswer;
    }
}
