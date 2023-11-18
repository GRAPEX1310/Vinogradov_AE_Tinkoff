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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final Path filePath;
    private static final Logger LOGGER = LogManager.getLogger();
    private final Pattern pairPattern = Pattern.compile("^(.*): (.*)$");
    private static final String OPENING_ERROR = "Error during file opening";
    private static final String READING_ERROR = "Error during file reading";
    private static final String WRITING_ERROR = "Error during file writing";
    private static final String STORAGE_PATH = "src/main/java/edu/hw6/Task1/Map_Storage.txt";

    public DiskMap() {
        filePath = Paths.get(STORAGE_PATH);

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
        List<Pair<String, String>> elements = getElementsArray();

        if (!elements.isEmpty()) {
            for (Pair<String, String> element : elements) {
                if (element.getLeft().equals(key)) {
                    return element.getRight();
                }
            }
        }

        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (key == null || value == null || containsKey(key)) {
            return null;
        }

        List<Pair<String, String>> elements = getElementsArray();
        elements.add(new ImmutablePair<>(key, value));
        elements.sort(Comparator.comparing(Pair::getLeft));
        putElementsArray(elements);

        return null;
    }

    @Override
    public String remove(Object key) {
        List<Pair<String, String>> elements = getElementsArray();

        if (!elements.isEmpty()) {
            putElementsArray(elements.stream().filter(element -> !element.getLeft().equals(key)).toList());
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        List<Pair<String, String>> elements = getElementsArray();

        for (var entry : m.entrySet()) {
            boolean correct = true;

            for (Pair<String, String> element : elements) {
                if (element.getLeft().equals(entry.getKey())) {
                    correct = false;
                    break;
                }
            }

            if (correct) {
                elements.add(new ImmutablePair<>(entry.getKey(), entry.getValue()));
                elements.sort(Comparator.comparing(Pair::getLeft));
                putElementsArray(elements);
            }
        }
    }

    @Override
    public void clear() {
        try (OutputStream outputStream = new BufferedOutputStream(
                Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {

            outputStream.write("".getBytes());

        } catch (IOException e) {
            LOGGER.error(WRITING_ERROR);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        List<Pair<String, String>> elements = getElementsArray();
        Set<String> setOfKeys = new HashSet<>();

        if (!elements.isEmpty()) {
            for (Pair<String, String> element : elements) {
                setOfKeys.add(element.getLeft());
            }
        }

        return setOfKeys;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return Objects.requireNonNull(getElementsArray().stream().map(Pair::getRight)).toList();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        List<Pair<String, String>> elements = getElementsArray();
        Set<Entry<String, String>> entriesSet = new HashSet<>();

        if (!elements.isEmpty()) {
            for (Pair<String, String> element : elements) {
                entriesSet.add(new ImmutablePair<>(element.getLeft(), element.getRight()));
            }
        }

        return entriesSet;
    }

    private boolean isMapContains(Object object, int mode) {
        boolean result = false;
        List<Pair<String, String>> elements = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String entry;

            while ((entry = bufferedReader.readLine()) != null) {

                Matcher matcher = pairPattern.matcher(entry);
                if (matcher.find() && matcher.group(mode).equals(object)) {
                    elements.add(new ImmutablePair<>(matcher.group(1), matcher.group(2)));
                }
            }
        } catch (IOException e) {
            LOGGER.error(READING_ERROR);
        }

        if (!elements.isEmpty()) {
            result = true;
        }

        return result;

    }

    private List<Pair<String, String>> getElementsArray() {
        List<Pair<String, String>> resultArray = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String entry;

            while ((entry = bufferedReader.readLine()) != null) {
                Matcher matcher = pairPattern.matcher(entry);

                if (matcher.find()) {
                    resultArray.add(new ImmutablePair<>(matcher.group(1), matcher.group(2)));
                }
            }
        } catch (IOException e) {
            LOGGER.error(READING_ERROR);
        }

        return resultArray;
    }

    private void putElementsArray(List<Pair<String, String>> elementsArray) {
        try (OutputStream outputStream = new BufferedOutputStream(
                Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {

            StringBuilder resultString = new StringBuilder();
            for (Pair<String, String> elemetsPair : elementsArray) {
                resultString.append(elemetsPair.getLeft()).append(": ")
                        .append(elemetsPair.getRight()).append(System.lineSeparator());

                outputStream.write(resultString.toString().getBytes());
                resultString.setLength(0);
            }
        } catch (IOException e) {
            LOGGER.error(WRITING_ERROR);
        }
    }
}
