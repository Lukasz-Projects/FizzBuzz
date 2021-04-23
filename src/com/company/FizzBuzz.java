package com.company;

import java.io.PrintStream;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;


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

    public void run() {
        IntStream.range(startInclusive, endExclusive)
                .mapToObj(
                        i -> divisorOutput.entrySet()
                                .stream()
                                .filter(divisorOutputEntry -> i % divisorOutputEntry.getKey() == 0)
                                .map(Map.Entry::getValue)
                                .reduce((s, s2) -> String.format("%s %s", s, s2))
                                .orElseGet(() -> Integer.toString(i))
                )
                .forEach(printStream::println);
    }

    public static void main(String[] args) {
        var divisorOutputMap = Map.of(
                3, "Fizz",
                5, "Buzz"
        );
        new FizzBuzz(
                System.out,
                1,
                101,
                new TreeMap<>(divisorOutputMap)
        ).run();
    }
}
