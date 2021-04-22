package com.company;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class FizzBuzz {
    private final List<Function<Integer, Stream<String>>> rules;
    private final int startInclusive, endExclusive;

    public FizzBuzz(List<Function<Integer, Stream<String>>> rules, int startInclusive, int endExclusive) {
        this.rules = rules;
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    public void run(){
        IntStream.range(startInclusive, endExclusive)
                .boxed()
                .map(integer ->  rules.stream()
                        .flatMap(integerStreamFunction -> integerStreamFunction.apply(integer))
                        .findFirst()
                        .orElseGet(() -> Integer.toString(integer))
                )
                .forEach(System.out::println);
    }



    public static void main(String[] args) {
        new FizzBuzz(
                returnRules(),
                1,
                101
        ).run();
    }


    private static List<Function<Integer, Stream<String>>> returnRules(){
        List<Function<Integer, Stream<String>>> rules = new LinkedList<>();
        rules.add(o -> {
            if(o % 15 == 0){
                return Stream.of("Fizz Buzz");
            }
            return Stream.empty();
        });

        rules.add(o -> {
            if(o % 5 == 0){
                return Stream.of("Buzz");
            }
            return Stream.empty();
        });

        rules.add(o -> {
            if(o % 3 == 0){
                return Stream.of("Fizz");
            }
            return Stream.empty();
        });
        return rules;
    }
}
