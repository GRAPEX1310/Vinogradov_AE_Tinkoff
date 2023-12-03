package edu.hw8;


import edu.hw8.Task3.LinearPasswordHack;
import edu.hw8.Task3.MultiThreadPasswordHack;
import edu.hw8.Task3.PasswordHack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Password hacks check")
    void testPasswordHack() {
        Map<String, String> hashedPasswords1 = new HashMap<>(Map.ofEntries(
                Map.entry("535ab76633d94208236a2e829ea6d888", "a.v.petrov"),//1310
                Map.entry("a19ebfcfae0ebf09b644a22d92c522dd", "a.e.vinogradov"),//sA13
                Map.entry("f357b162d8217b3d42cff07aaeb99ce5", "k.p.maslov")//baza
        ));

        var hashedPasswords2 = new HashMap<>(hashedPasswords1);

        PasswordHack linearPasswordHack = new LinearPasswordHack(hashedPasswords1);
        PasswordHack multiThreadPasswordHack = new MultiThreadPasswordHack(hashedPasswords2);

        long t1Start = System.currentTimeMillis();
        Map<String, String> resultLinear = linearPasswordHack.nextPassword(4);
        long t1 = System.currentTimeMillis() - t1Start;

        long t2Start = System.currentTimeMillis();
        Map<String, String> resultMultiThread = multiThreadPasswordHack.nextPassword(4);
        long t2 = System.currentTimeMillis() - t2Start;


        System.out.println(t1);
        System.out.println(t2);
        assertThat(resultLinear).isEqualTo(resultMultiThread);
        assertThat(resultLinear.size()).isEqualTo(3);
    }
}
