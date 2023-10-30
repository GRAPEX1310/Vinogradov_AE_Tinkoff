package edu.hw2.Task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task3.class);
    private static final String SUCCESSFUL_CONNECTION = "Connection is established";
    private static final String CLOSED_CONNECTION = "Connection was closed";
    private static final String CONNECTION_ERROR = "Connection error";
    private static final String CONNECTION_CLOSING_ERROR = "Connection closing was failed";
    private static final String ATTEMPTS_LIMIT_EXCEED = "Limit of attempts was exceeded";
    private static final int PERCENT_100 = 100;
    private static final int PERCENT_95 = 95;
    private static final int PERCENT_10 = 10;




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
