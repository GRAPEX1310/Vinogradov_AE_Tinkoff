package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {

    private static final int PERCENT_100 = 100;
    private static final int PERCENT_10 = 10;

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
