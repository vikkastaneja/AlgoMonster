package com.algo.innov8r.twopointers;

import java.util.List;
import java.util.stream.Collectors;

public class AllAnagramsInString {
    public static List<Integer> findAllAnagrams(String original, String check) {
        // WRITE YOUR BRILLIANT CODE HERE
        return List.of();
    }

    public static void main(String[] args) {
        String original = "";
        String check = "";
        List<Integer> res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
