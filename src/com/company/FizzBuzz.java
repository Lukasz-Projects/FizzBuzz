package com.company;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


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
                .boxed()
                .map(
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
