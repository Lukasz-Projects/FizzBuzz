package com.company;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;


public class FizzBuzz {
    private final int startInclusive, endExclusive;
    private final SortedMap<Integer,String> divisorOutput;

    public FizzBuzz(int startInclusive, int endExclusive, SortedMap<Integer, String> divisorOutput) {
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
        this.divisorOutput = divisorOutput;
    }

    public void run(){
        IntStream.range(startInclusive, endExclusive)
                .mapToObj(
                        i -> divisorOutput.entrySet()
                                .stream()
                                .filter(divisorOutputEntry -> i % divisorOutputEntry.getKey() == 0)
                                .map(Map.Entry::getValue)
                                .reduce((s, s2) -> String.format("%s %s",s,s2))
                                .orElseGet(() -> Integer.toString(i))
                )
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        var divisorOutputMap = Map.of(
                3, "Fizz",
                5, "Buzz"
        );
        new FizzBuzz(
                1,
                101,
                new TreeMap<>(divisorOutputMap)
        ).run();
    }
}
