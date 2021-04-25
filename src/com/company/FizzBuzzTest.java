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
    private static FakePrintStream fakePrintStream;

    @BeforeAll
    static void beforeAll() {
        divisorOutput = new TreeMap<>();
        divisorOutput.put(3,FIZZ);
        divisorOutput.put(5,BUZZ);
        fakePrintStream = new FakePrintStream();
    }

    @BeforeEach
    void before(){
        fakePrintStream.clear();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 12})
    void run_fizz(int number) {
        new FizzBuzz(fakePrintStream,number,number+1,divisorOutput).print();
        assertEquals(FIZZ,fakePrintStream.getLastPrinted());
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 35})
    void run_buzz(int number) {
        new FizzBuzz(fakePrintStream,number,number+1,divisorOutput).print();
        assertEquals(BUZZ,fakePrintStream.getLastPrinted());
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void run_fizzbuzz(int number) {
        new FizzBuzz(fakePrintStream,number,number+1,divisorOutput).print();
        assertEquals(String.format("%s %s",FIZZ,BUZZ),fakePrintStream.getLastPrinted());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 7, 11})
    void run_number(int number) {
        new FizzBuzz(fakePrintStream,number,number+1,divisorOutput).print();
        assertEquals(Integer.toString(number),fakePrintStream.getLastPrinted());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 99, 250, 2_065_321})
    void run_countLines(int number) {
        new FizzBuzz(fakePrintStream,1,1+number,divisorOutput).print();
        assertEquals(number,fakePrintStream.getAllPrintedAsString().lines().count());
    }

    @AfterAll
    static void avoid_memory_leak(){
        divisorOutput = null;
        fakePrintStream = null;
    }

    static class FakePrintStream extends PrintStream{
        private String lastPrinted;
        private StringBuilder allPrinted;

        public FakePrintStream() {
            super(new ByteArrayOutputStream());
            allPrinted = new StringBuilder();
            lastPrinted = "";
        }

        @Override
        public void println(String print){
            lastPrinted = print;
            allPrinted.append(print);
            allPrinted.append('\n');
        }

        @Override
        public void print(String print){
            lastPrinted = print;
            allPrinted.append(print);
        }

        public String getLastPrinted() {
            return lastPrinted;
        }

        public String getAllPrintedAsString() {
            return allPrinted.toString();
        }

        public void clear(){
            allPrinted = new StringBuilder();
            lastPrinted = "";
        }
    }
}