package edu.hw2.Task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StableConnection implements Connection {

    private static final String SUCCESSFUL_CONNECTION = "Connection is established";
    private static final String CLOSED_CONNECTION = "Connection was closed";

    private static final Logger LOGGER = LoggerFactory.getLogger(Task3.class);

    @Override
    public void execute(String command) {
        LOGGER.info(SUCCESSFUL_CONNECTION);
    }

    @Override
    public void close() throws Exception {
        LOGGER.info(CLOSED_CONNECTION);

    }
}
