package edu.hw6.Task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompositeFilter {

    private CompositeFilter() {
    }

    public static final AbstractFilter IS_REGULAR = Files::isRegularFile;
    public static final AbstractFilter IS_READABLE = Files::isReadable;
    public static final AbstractFilter IS_WRITABLE = Files::isWritable;

    public static AbstractFilter largerThan(long size) {
        return (entry) -> Files.size(entry) > size;
    }

    public static AbstractFilter lessThan(long size) {
        return (entry) -> Files.size(entry) < size;
    }

    public static AbstractFilter fileName(String name) {
        return entry -> entry.getFileName().toString().equals(name);
    }

    public static AbstractFilter globMatches(String globString) {
        return (entry) -> entry.toString().matches(globString);
    }

    public static AbstractFilter regexContains(String regexString) {
        return (entry) -> {
            Pattern pattern = Pattern.compile(regexString);
            Matcher matcher = pattern.matcher(entry.getFileName().toString());
            return matcher.find();
        };
    }

    public static AbstractFilter magicNumber(char... chars) {
        final int countChars = chars.length;
        return (entry) -> {
            char[] charsFromFile = new char[countChars];

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(entry.toFile()))) {
                bufferedReader.read(charsFromFile, 0, countChars);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return Arrays.equals(chars, charsFromFile);
        };
    }
}
