package com.algo.innov8r.twopointers;

import com.algo.innov8r.common.Assertion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TwoSumSorted {
    public static List<Integer> twoSumSorted(List<Integer> arr, int target) {
        int i = 0;
        int j = arr.size() - 1;
        while (i < j) {
            int left = arr.get(i);
            int right = arr.get(j);
            if (left + right < target) {
                i++;
            } else if (left + right > target) {
                j--;
            } else {
                break;
            }
        }
        return List.of(i, j);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        String val = "2 3 5 8 11 15";
        List<Integer> nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> result = twoSumSorted(nums, 5);
        Assertion.assertEquals("0 1", result.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        val = "2 5 10 12 30 100";
        nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        result = twoSumSorted(nums, 22);
        Assertion.assertEquals("2 3", result.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        val = "1 2 3 10 20 30 50 100";
        nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        result = twoSumSorted(nums, 101);
        Assertion.assertEquals("0 7", result.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        val = "1 2";
        nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        result = twoSumSorted(nums, 3);
        Assertion.assertEquals("0 1", result.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        val = "100 1000 2001 3000 4000 5000";
        nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        result = twoSumSorted(nums, 5001);
        Assertion.assertEquals("2 3", result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
