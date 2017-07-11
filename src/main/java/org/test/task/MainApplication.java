package org.test.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class MainApplication {

    private static final String FILE_NAME = "./src/main/resources/lyrics.txt";
    private static final int COUNT_TO_OUTPUT = 5;
    private static final PrintStream OUT = System.out;

    public static void main(String[] args) throws IOException {

        File textFile = new File(FILE_NAME);
        if (!textFile.exists() && !textFile.isDirectory()) {
            throw new FileNotFoundException("Sorry, your entered file doesn't exist or this file is directory");
        }

        List<String> readLines = new ArrayList<>();
        try {
            readLines.addAll(readAllLinesFromFile(textFile, Charset.defaultCharset()));
        } catch (IOException | URISyntaxException e) {
            throw new IOException(
                    "Happened some problem with content reading. Please verify if content is created correctly."
                            + "Original problem:" + e);

        }

        WordCounter wordCounter = new WordCounter(readLines);
        Stream<Entry<String, Long>> wordsToOut = wordCounter.getCountElement(COUNT_TO_OUTPUT);
    }

    private static List<String> readAllLinesFromFile(File textFile, Charset encoding)
            throws IOException, URISyntaxException {
        return Files.readAllLines(textFile.toPath(), encoding);
    }
}
