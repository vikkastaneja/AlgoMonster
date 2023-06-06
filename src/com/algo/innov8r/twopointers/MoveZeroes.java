package com.algo.innov8r.twopointers;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MoveZeroes {
    public static void moveZeros(List<Integer> nums) {
        // 1 2 7 0 0 0
        //     i
        //           j
        int i = 0;
        int j = 0;
        while (j < nums.size()) {
            while (i < nums.size() && nums.get(i) != 0) {
                i++;
            }

            j = i + 1;
            while (j < nums.size() && nums.get(j) == 0) {
                j++;
            }

            if (j < nums.size()) {
                int temp = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, temp);
            }
        }
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        String val = "1 0 2 0 0 7";
        List<Integer> nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        moveZeros(nums);
        Assert.assertEquals("1 2 7 0 0 0", nums.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        val = "3 1 0 1 3 8 9";
        nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        moveZeros(nums);
        Assert.assertEquals("3 1 1 3 8 9 0", nums.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        val = "0 0 9 4 0 0 3 8 0";
        nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        moveZeros(nums);
        Assert.assertEquals("9 4 3 8 0 0 0 0 0", nums.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
