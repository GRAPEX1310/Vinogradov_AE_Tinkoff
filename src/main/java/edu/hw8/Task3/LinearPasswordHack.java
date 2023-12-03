package edu.hw8.Task3;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class LinearPasswordHack implements PasswordHack {

    private final MessageDigest md5;
    private final Map<String, String> hashedPasswords;
    private final Map<String, String> resultPasswords;
    private final Queue<String> passwords;
    private static final int MD5_ENCODE = 16;

    public LinearPasswordHack(Map<String, String> hashedPasswords) {
        this.hashedPasswords = hashedPasswords;
        this.resultPasswords = new HashMap<>();
        this.passwords = new ArrayDeque<>();

        try {
            this.md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> nextPassword(int maxLength) {
        passwords.add("");

        while (!hashedPasswords.isEmpty() && !passwords.isEmpty()) {
            String currentPassword = passwords.poll();

            try {
                String currentHash = (new BigInteger(1,
                        md5.digest(currentPassword.getBytes(StandardCharsets.UTF_8)))).toString(MD5_ENCODE);

                if (hashedPasswords.containsKey(currentHash)) {
                    resultPasswords.put(currentPassword, hashedPasswords.get(currentHash));
                    hashedPasswords.remove(currentHash);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            passwordGenerator(currentPassword, maxLength);
        }

        passwords.clear();
        return resultPasswords;
    }

    @Override
    public void passwordGenerator(String basePassword, int maxLength) {
        if (basePassword.length() < maxLength) {
            for (int index = ((int) 'A'); index <= ((int) 'Z'); index++) {
                passwords.add(basePassword + (char) index);
            }
            for (int index = ((int) 'a'); index <= ((int) 'z'); index++) {
                passwords.add(basePassword + (char) index);
            }

            for (int index = ((int) '0'); index <= ((int) '9'); index++) {
                passwords.add(basePassword + (char) index);
            }
        }
    }
}
