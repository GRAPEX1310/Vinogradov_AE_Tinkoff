package edu.hw8.Task3;

import java.util.Map;

public interface PasswordHack {

    Map<String, String> nextPassword(int maxLength);

    void passwordGenerator(String basePassword, int maxLength);
}
