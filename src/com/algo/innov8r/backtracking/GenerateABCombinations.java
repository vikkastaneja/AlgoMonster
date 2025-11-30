package com.algo.innov8r.backtracking;

import com.algo.innov8r.common.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Given a non-negative integer n, find all n-letter words composed by 'a' and 'b', return them in a list of strings in lexicographical order.
 */
public class GenerateABCombinations {
    public static List<String> letterCombination(int n) {
        List<String> returnString = new ArrayList<>();
        dfs(n, returnString, 0, "");
        return returnString;
    }

    private static void dfs(int n, List<String> list, int start, String tempString) {
        if (start == n) {
            list.add(tempString);
            return;
        }

        for(String s : new String[] {"a", "b"}) {
            dfs(n, list, start + 1, tempString + s);
        }
    }

    public static void main(String[] args) {
        List<String> res = letterCombination(2);

        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue(res.equals(Arrays.asList("aa", "ab", "ba", "bb")));

        res = letterCombination(1);

        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue(res.equals(Arrays.asList("a", "b")));

        res = letterCombination(3);

        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue(res.equals(Arrays.asList("aaa", "aab","aba","abb","baa","bab","bba","bbb")));

        res = letterCombination(0);

        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue(res.equals(Arrays.asList("")));
    }
}
