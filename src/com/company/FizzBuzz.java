package com.company;

import java.io.PrintStream;
import java.util.Map;
import java.util.SortedMap;
import java.util.AbstractMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class FizzBuzz {
    private final PrintStream printStream;
    private final int startInclusive, endExclusive;
    private final SortedMap<Integer,String> divisorOutput;

    public FizzBuzz(PrintStream printStream, int startInclusive, int endExclusive, SortedMap<Integer, String> divisorOutput) {
        this.printStream = printStream;
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
        this.divisorOutput = divisorOutput;
    }

    public void print() {
        IntStream.range(startInclusive, endExclusive)
                .mapToObj(
                        i -> divisorOutput.entrySet()
                                .stream()
                                .filter(divisorOutputEntry -> i % divisorOutputEntry.getKey() == 0)
                                .map(Map.Entry::getValue)
                                .collect(Collectors.joining(" "))
                                .transform(s -> s.isEmpty() ? Integer.toString(i) : s )
                )
                .forEachOrdered(printStream::println);
    }

    public static void main(String[] args) {
        var divisorOutputMap = Stream.of(
                new AbstractMap.SimpleImmutableEntry<>(3, "Fizz"),
                new AbstractMap.SimpleImmutableEntry<>(5, "Buzz")
        )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (s, s2) -> s2,
                        TreeMap::new
                ));
        new FizzBuzz(
                System.out,
                1,
                101,
                divisorOutputMap
        ).print();
    }
}
