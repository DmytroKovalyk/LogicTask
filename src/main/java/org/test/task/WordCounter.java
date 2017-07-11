package org.test.task;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WordCounter {

    private static final String WORD_PATTERN = "\\W+";

    private final Map<String, Long> _wordCounter = new LinkedHashMap<>();

    public WordCounter(List<String> lines) {
        Map<String, Long> wordCounter =
                lines.stream().flatMap(line -> Pattern.compile(WORD_PATTERN).splitAsStream(line))
                        .collect(groupingBy(Function.identity(), counting()));

        _wordCounter.putAll(
                wordCounter.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public Stream<Entry<String, Long>> getCountElement(int count) {
        return _wordCounter.entrySet().stream().limit(count);
    }

    // public static void main(String[] args) {
    // String str = "Hark. How the bells, sweet silver bells";
    // String str1 = "All seem to say, Throw cares away.";
    //
    // Pattern.compile(WORD_PATTERN).splitAsStream(str1).forEach(s -> System.out.print(s + " "));
    // }

}
