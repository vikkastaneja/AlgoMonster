package com.algo.innov8r.common;

import java.util.*;

public class Assertion {

    // ===== assertEquals =====
    public static <T> void assertEquals(T expected, T actual) {
        if (!Objects.equals(expected, actual)) {
            fail("Expected: " + expected + " but got: " + actual);
        }
        pass("assertEquals", expected, actual);
    }

    public static <T> void assertEquals(String message, T expected, T actual) {
        if (!Objects.equals(expected, actual)) {
            fail(message + " | Expected: " + expected + " but got: " + actual);
        }
        pass("assertEquals", expected, actual);
    }

    // ===== assertTrue =====
    public static void assertTrue(boolean condition) {
        if (!condition) {
            fail("Expected condition to be true");
        }
        pass("assertTrue", true, condition);
    }

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message + " | Expected condition to be true");
        }
        pass("assertTrue", true, condition);
    }

    // ===== assertFalse =====
    public static void assertFalse(boolean condition) {
        if (condition) {
            fail("Expected condition to be false");
        }
        pass("assertFalse", false, condition);
    }

    public static void assertFalse(String message, boolean condition) {
        if (condition) {
            fail(message + " | Expected condition to be false");
        }
        pass("assertFalse", false, condition);
    }

    // ===== assertArrayEquals =====
    public static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            fail("Expected: " + Arrays.toString(expected) +
                 " but got: " + Arrays.toString(actual));
        }
        pass("assertArrayEquals",
            Arrays.toString(expected),
            Arrays.toString(actual));
    }

    public static void assertArrayEquals(String message, int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            fail(message + " | Expected: " + Arrays.toString(expected) +
                 " but got: " + Arrays.toString(actual));
        }
        pass("assertArrayEquals",
            Arrays.toString(expected),
            Arrays.toString(actual));
    }

    // ===== assertListEquals =====
    public static <T> void assertListEquals(List<T> expected, List<T> actual) {
        if (!Objects.equals(expected, actual)) {
            fail("Expected: " + expected + " but got: " + actual);
        }
        pass("assertListEquals", expected, actual);
    }

    public static <T> void assertListEquals(String message, List<T> expected, List<T> actual) {
        if (!Objects.equals(expected, actual)) {
            fail(message + " | Expected: " + expected + " but got: " + actual);
        }
        pass("assertListEquals", expected, actual);
    }

    public static <K, V> void assertMapEquals(Map<K, V> expected, Map<K, V> actual) {
        if (!Objects.equals(expected, actual)) {
            fail("Expected: " + expected + " but got: " + actual);
        }
        pass("assertMapEquals", expected, actual);
    }

    public static <K, V> void assertMapEquals(String message, Map<K,V> expected, Map<K,V> actual) {
        if (!Objects.equals(expected, actual)) {
            fail(message + " | Expected: " + expected + " but got: " + actual);
        }
        pass("assertMapEquals", expected, actual);
    }

    public static <T> void assertStackEquals(Stack<T> expected, Stack<T> actual) {
        if (!Objects.equals(expected, actual)) {
            fail("Expected: " + expected + " but got: " + actual);
        }

        pass("assertStackEquals", expected, actual);
    }

    public static <T> void assertStackEquals(String message, Stack<T> expected, Stack<T> actual) {
        if (!Objects.equals(expected, actual)) {
            fail(message + " | Expected: " + expected + " but got: " + actual);
        }

        pass("assertStackEquals", expected, actual);
    }

    public static <T> void assertQueueEquals(Queue<T> expected, Queue<T> actual) {
        if (!Objects.equals(expected, actual)) {
            fail("Expected: " + expected + " but got: " + actual);
        }

        pass("assertQueueEquals", expected, actual);
    }

    public static <T> void assertQueueEquals(String message, Queue<T> expected, Queue<T> actual) {
        if (!Objects.equals(expected, actual)) {
            fail(message + " | Expected: " + expected + " but got: " + actual);
        }

        pass("assertQueueEquals", expected, actual);
    }

    private static void fail(String message) {
        System.err.println("Assertion failed: " + message);
        System.exit(1);
    }

    private static void pass(String type, Object expected, Object actual) {
        System.out.println(type + " passed: " + expected + " == " + actual);
    }
}
