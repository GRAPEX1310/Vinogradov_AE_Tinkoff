package edu.hw2.Task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FaultyConnection implements Connection {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task3.class);

    private static final String SUCCESSFUL_CONNECTION = "Connection is established";
    private static final String CLOSED_CONNECTION = "Connection was closed";

    private static final int PERCENT_100 = 100;
    private static final int PERCENT_95 = 95;

    @Override
    public void execute(String command) {
        double connectionProbability = Math.random() * PERCENT_100;

        if (connectionProbability <= PERCENT_95) {
            throw new ConnectionException();
        } else {
            LOGGER.info(SUCCESSFUL_CONNECTION);
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info(CLOSED_CONNECTION);
    }
}
