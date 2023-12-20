package edu.hw6.Task1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final Path filePath;
    private Map<String, String> cache;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String OPENING_ERROR = "Error during file opening";
    private static final String READING_ERROR = "Error during file reading";
    private static final String WRITING_ERROR = "Error during file writing";

    public DiskMap(String path) {
        filePath = Paths.get(path);
        cache = new HashMap<>();

        try (OutputStream outputStream = new BufferedOutputStream(
                Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            outputStream.write("".getBytes());
        } catch (IOException e) {
            LOGGER.error(OPENING_ERROR);
        }
    }

    @Override
    public int size() {
        int sizeCount = 0;

        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            while (bufferedReader.readLine() != null) {
                sizeCount++;
            }
        } catch (IOException e) {
            LOGGER.error(READING_ERROR);
        }

        return sizeCount;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        return isMapContains(key, 1);
    }

    @Override
    public boolean containsValue(Object value) {
        return isMapContains(value, 2);
    }

    @Override
    public String get(Object key) {
        return cache.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (key == null || value == null || containsKey(key)) {
            return null;
        }
        cache.put(key, value);
        putElements(cache);
        return null;
    }

    @Override
    public String remove(Object key) {
        cache.remove(key);
        if (!cache.isEmpty()) {
            putElements(cache);
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry : m.entrySet()) {
            boolean correct = true;

            for (var element : cache.entrySet()) {
                if (element.getKey().equals(entry.getKey())) {
                    correct = false;
                    break;
                }
            }

            if (correct) {
                cache.put(entry.getKey(), entry.getValue());
                putElements(cache);
            }
        }
    }

    @Override
    public void clear() {
        try (OutputStream outputStream = new BufferedOutputStream(
                Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {

            outputStream.write("".getBytes());
            cache.clear();
        } catch (IOException e) {
            LOGGER.error(WRITING_ERROR);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return cache.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        Collection<String> valuesResult = new ArrayList<>();
        for (var element : cache.entrySet()) {
            valuesResult.add(element.getValue());
        }
        return valuesResult;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return cache.entrySet();
    }

    private boolean isMapContains(Object object, int mode) {
        if (object == null) {
            return false;
        } else if (mode == 1) {
            return cache.containsKey(object);
        } else {
            return cache.containsValue(object);
        }
    }

    private void putElements(Map<String, String> elementsMap) {
        try (OutputStream outputStream = new BufferedOutputStream(
                Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {

            StringBuilder resultString = new StringBuilder();

            for (var elementsPair : elementsMap.entrySet()) {
                resultString.append(elementsPair.getKey()).append(": ")
                        .append(elementsPair.getValue()).append(System.lineSeparator());

                outputStream.write(resultString.toString().getBytes());
                resultString.setLength(0);
            }
            cache = elementsMap;
        } catch (IOException e) {
            LOGGER.error(WRITING_ERROR);
        }
    }

    private String[] isValid(String data) {
        String[] result = data.split(": ");
        if (result.length == 2) {
            return result;
        } else {
            return null;
        }
    }
}
