package com.algo.innov8r.twopointers;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SubarraySum {
    public static int maxSubarraySum(List<Integer> nums, int k) {
        if ( k > nums.size()) return  Integer.MIN_VALUE;
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0, j = 0; j < nums.size();) {
            if (k-- > 0) {
                currentSum += nums.get(j);
                j++;
            } else {
                currentSum += nums.get(j);
                currentSum -= nums.get(i);
                i++;
                j++;
                maxSum = Math.max(currentSum, maxSum);
            }
        }

        return maxSum;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        String val = "1 2 3 7 4 1";
        List<Integer> nums = splitWords(val).stream().map(Integer::parseInt).collect(Collectors.toList());
        int k = 3;
        int res = maxSubarraySum(nums, k);
        Assert.assertEquals(14, res);
    }
}
