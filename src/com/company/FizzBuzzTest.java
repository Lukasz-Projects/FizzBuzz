package com.company;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {
    private static final String FIZZ = "Fizz", BUZZ = "Buzz";
    private static SortedMap<Integer,String> divisorOutput;
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;

    @BeforeAll
    static void beforeAll() {
        divisorOutput = new TreeMap<>();
        divisorOutput.put(3,FIZZ);
        divisorOutput.put(5,BUZZ);
    }

    @BeforeEach
    void before(){
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 12})
    void run_fizz(int number) {
        new FizzBuzz(printStream,number,number+1,divisorOutput).run();
        assertEquals(FIZZ,byteArrayOutputStream.toString().trim());
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 35})
    void run_buzz(int number) {
        new FizzBuzz(printStream,number,number+1,divisorOutput).run();
        assertEquals(BUZZ,byteArrayOutputStream.toString().trim());
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void run_fizzbuzz(int number) {
        new FizzBuzz(printStream,number,number+1,divisorOutput).run();
        assertEquals(String.format("%s %s",FIZZ,BUZZ),byteArrayOutputStream.toString().trim());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 7, 11})
    void run_number(int number) {
        new FizzBuzz(printStream,number,number+1,divisorOutput).run();
        assertEquals(Integer.toString(number),byteArrayOutputStream.toString().trim());
    }

    @AfterAll
    static void avoid_memory_leak(){
        divisorOutput = null;
    }
}