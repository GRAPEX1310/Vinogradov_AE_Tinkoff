package edu.hw6;

import edu.hw6.Task6.PortScanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    @DisplayName("Test PortScanner working")
    void testPortScanner() {
        File ports = new File("src/main/java/edu/hw6/Task6/Ports.txt");
        assertThat(ports.exists()).isEqualTo(true);

        assertThat(PortScanner.portScanner().size()).isNotZero();
    }
}
