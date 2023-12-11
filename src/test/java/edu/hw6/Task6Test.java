package edu.hw6;

import edu.hw6.Task6.PortScanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    private static final String PORTS_PATH = "src/main/resources/Ports.txt";

    @Test
    @DisplayName("Test PortScanner working")
    void testPortScanner() {
        File ports = new File(PORTS_PATH);
        assertThat(ports.exists()).isEqualTo(true);

        var result = PortScanner.portScanner();
        assertThat(result.size()).isNotZero();
    }

    @Test
    @DisplayName("Is someone use port")
    void testBusyPort() {
        File ports = new File(PORTS_PATH);
        assertThat(ports.exists()).isEqualTo(true);
        var result = PortScanner.portScanner();

        boolean isPortBusy = false;
        String checkString = "TCP  |  135  |  Microsoft_EPMAP";
        for (String port : result) {
            if (checkString.equals(port)) {
                isPortBusy = true;
                break;
            }
        }

        assertThat(isPortBusy).isTrue();
    }
}
