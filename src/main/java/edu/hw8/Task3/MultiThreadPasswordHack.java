package edu.hw8.Task3;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

public class MultiThreadPasswordHack implements PasswordHack {

    private final MessageDigest md5;
    private final ReadWriteLock lock;

    private final Map<String, String> hashedPasswords;
    private final Map<String, String> resultPasswords;
    private final Queue<String> passwords;

    private static final int THREADS_AMOUNT = 6;
    private static final int MD5_ENCODE = 16;

    public MultiThreadPasswordHack(Map<String, String> hashedPasswords) {
        this.hashedPasswords = hashedPasswords;
        this.passwords = new ArrayDeque<>();
        this.resultPasswords = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> nextPassword(int maxLength) {
        passwords.add("");

        Thread[] threads = Stream.generate(() -> new Thread(() -> {

                    while (!hashedPasswords.isEmpty() && !passwords.isEmpty()) {
                        String currentPassword = null;
                        lock.writeLock().lock();
                        try {
                            currentPassword = passwords.poll();
                        } finally {
                            lock.writeLock().unlock();
                        }

                        if (currentPassword == null) {
                            continue;
                        }

                        try {
                            String currentHash = (new BigInteger(1,
                                    md5.digest(currentPassword.getBytes(StandardCharsets.UTF_8)))).toString(MD5_ENCODE);

                            lock.writeLock().lock();
                            try {

                                if (hashedPasswords.containsKey(currentHash)) {
                                    resultPasswords.put(currentPassword, hashedPasswords.get(currentHash));
                                    hashedPasswords.remove(currentHash);
                                }
                            } finally {
                                lock.writeLock().unlock();
                            }
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }

                        passwordGenerator(currentPassword, maxLength);
                    }
                }))
                .limit(THREADS_AMOUNT)
                .toArray(Thread[]::new);

        Arrays.stream(threads).forEach(Thread::start);

        for (int i = 0; i < THREADS_AMOUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                threads[i].interrupt();
            }
        }

        passwords.clear();
        return resultPasswords;
    }

    @Override
    public synchronized void passwordGenerator(String basePassword, int maxLength) {
        if (basePassword.length() < maxLength) {

            lock.writeLock().lock();
            try {

                for (int index = ((int) 'A'); index <= ((int) 'Z'); index++) {
                    passwords.add(basePassword + (char) index);
                }
                for (int index = ((int) 'a'); index <= ((int) 'z'); index++) {
                    passwords.add(basePassword + (char) index);
                }

                for (int index = ((int) '0'); index <= ((int) '9'); index++) {
                    passwords.add(basePassword + (char) index);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
