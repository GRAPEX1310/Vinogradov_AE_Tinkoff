package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

    private boolean serverIsCorrect;

    private static final int PORT = 4004;
    private static final int THREADS_AMOUNT = 6;

    private static final String ERROR = "Problems with request!";
    private static final String STOP = "/stop";

    private final ExecutorService executorService;
    private static final Logger LOGGER = LogManager.getLogger();

    public Server() {
        serverIsCorrect = false;
        executorService = Executors.newFixedThreadPool(THREADS_AMOUNT);
    }

    public void start() {
        serverIsCorrect = true;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (serverIsCorrect) {
                Socket clientSocket = serverSocket.accept();

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String message = bufferedReader.readLine();

                if (message.equals(STOP)) {
                    serverIsCorrect = false;
                }

                executorService.execute(new QuoteRequest(clientSocket, message));
            }

            executorService.shutdown();
        } catch (IOException exception) {
            LOGGER.error(ERROR);
        }
    }
}
