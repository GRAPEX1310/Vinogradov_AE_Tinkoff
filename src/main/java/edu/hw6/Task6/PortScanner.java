package edu.hw6.Task6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortScanner {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern PARSE_PORTS_PATTERN = Pattern.compile("([0-9]{1,5}) ([A-z0-9_\\-/]+) ([A-z|/]+)");
    private static final int MAX_PORTS_NUMBER = 49151;

    private static final String OPENING_ERROR = "Error during file opening";
    private static final String PARAMS_SEPARATOR = "  |  ";
    private static final String TABLE_DESCRIPTION = "PROTOCOL  |  PORT  |  SERVICE";
    private static final String PORTS_FILEPATH = "src/main/java/edu/hw6/Task6/Ports.txt";

    private PortScanner() {

    }

    public static ArrayList<String> portScanner() {
        LOGGER.info(TABLE_DESCRIPTION);

        ArrayList<String> busyPorts = new ArrayList<>();

        for (int portNumber = 0; portNumber <= MAX_PORTS_NUMBER; portNumber++) {
            StringBuilder resultPortString = new StringBuilder();

            boolean currentPortFree = true;

            try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
                serverSocket.setReuseAddress(true);
            } catch (IOException e) {
                resultPortString.append("TCP" + PARAMS_SEPARATOR).append(portNumber);
                currentPortFree = false;
            }

            if (currentPortFree) {
                try (DatagramSocket datagramSocket = new DatagramSocket(portNumber)) {
                    datagramSocket.setReuseAddress(true);
                    continue;
                } catch (IOException e) {
                    resultPortString.append("UDP" + PARAMS_SEPARATOR).append(portNumber);
                }
            }

            boolean serviceFound = false;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PORTS_FILEPATH))) {
                String currentPort;
                while ((currentPort = bufferedReader.readLine()) != null) {
                    Matcher matcher = PARSE_PORTS_PATTERN.matcher(currentPort);
                    if (matcher.find() && (Integer.parseInt(matcher.group(1))) == portNumber) {
                        serviceFound = true;
                        String currentPortService = matcher.group(2);
                        resultPortString.append(PARAMS_SEPARATOR).append(currentPortService);
                    }
                }
            } catch (IOException e) {
                LOGGER.error(OPENING_ERROR);
                throw new RuntimeException(e);
            }

            if (!serviceFound) {
                resultPortString.append(PARAMS_SEPARATOR).append("N/A");
            }

            busyPorts.add(resultPortString.toString());
            LOGGER.info(resultPortString.toString());
        }

        return busyPorts;
    }
}
