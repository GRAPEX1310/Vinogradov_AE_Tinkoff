package edu.hw2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task3 {

    private final static Logger LOGGER = LoggerFactory.getLogger(Task3.class);
    private final static String SUCCESSFUL_CONNECTION = "Connection is established";
    private final static String CLOSED_CONNECTION = "Connection was closed";
    private final static String CONNECTION_ERROR = "Connection error";
    private final static String CONNECTION_CLOSING_ERROR = "Connection closing was failed";
    private final static String ATTEMPTS_LIMIT_EXCEED = "Limit of attempts was exceeded";
    private final static int PERCENT_100 = 100;
    private final static int PERCENT_95 = 95;
    private final static int PERCENT_10 = 10;

    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class StableConnection implements Connection {

        @Override
        public void execute(String command) {
            LOGGER.info(SUCCESSFUL_CONNECTION);
        }

        @Override
        public void close() throws Exception {
            LOGGER.info(CLOSED_CONNECTION);

        }
    }

    public static class FaultyConnection implements Connection {

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

    public static class DefaultConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            double connectionProbability = Math.random() * PERCENT_100;

            if (connectionProbability >= PERCENT_10) {
                return new StableConnection();
            } else {
                return new FaultyConnection();
            }
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public static class ConnectionException extends RuntimeException {
    }

    public static final class PopularCommandExecutor {

        private final ConnectionManager manager = new DefaultConnectionManager();
        private final int maxAttempts = 10;

        public void updatePackages() {
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) {

            int attemptCounter = 0;
            Connection connection = manager.getConnection();
            boolean limitExceed = false;

            while (attemptCounter < maxAttempts) {
                try {
                    connection = manager.getConnection();
                    connection.execute(command);

                    limitExceed = true;
                    break;
                } catch (ConnectionException connectionException) {
                    LOGGER.info(CONNECTION_ERROR);
                }

                attemptCounter++;

                try {
                    connection.close();
                } catch (Exception exception) {
                    LOGGER.info(CONNECTION_CLOSING_ERROR);
                    limitExceed = true;
                    break;
                }
            }

            if (!limitExceed) {
                LOGGER.info(ATTEMPTS_LIMIT_EXCEED);
            }
        }
    }
}
