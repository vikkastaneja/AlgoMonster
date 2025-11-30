package com.algo.innov8r.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import com.algo.innov8r.common.Assertion;
import java.util.List;

import com.algo.innov8r.common.Assertion;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 */
public class PartitioningAStringIntoPalindromes {
    public static List<List<String>> partition(String s) {
        List<List<String>> retList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        dfs(s, 0, list, retList);
        return retList;
    }

    private static void dfs(String s, int currentIndex, List<String> list, List<List<String>> retList) {
        if (currentIndex == s.length()) {
            List<String> temp = new ArrayList<>(list);
            retList.add(temp);
            return;
        }

        for (int i = currentIndex; i < s.length(); i++) {
            if(isPalindrome(s, currentIndex, i)) {
                list.add(s.substring(currentIndex, i+1));
                dfs(s, i+1, list, retList);
                list.remove(list.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> res = partition(s);
        List<List<String>> expected = Arrays.asList(Arrays.asList("a", "a", "b"), Arrays.asList("aa", "b"));

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }

        res = partition("aba");
        expected = Arrays.asList(Arrays.asList("a", "b", "a"), Arrays.asList("aba"));

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }
    }
}
