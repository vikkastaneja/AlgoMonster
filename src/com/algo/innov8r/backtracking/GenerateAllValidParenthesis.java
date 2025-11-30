package com.algo.innov8r.backtracking;

import com.algo.innov8r.common.Assertion;

import java.util.*;


public class GenerateAllValidParenthesis {
    public static List<String> generateParentheses(int n) {
        List<String> retStrings = new ArrayList<>();
        dfs(retStrings, n, new StringBuilder(), 0, 0);
        return retStrings;
    }

    private static void dfs(List<String> list, int n, StringBuilder currentString, int left, int right) {
        if (right == n) {
            list.add(new String(currentString));
            return;
        }

        if (left < n) {
            currentString.append("(");
            dfs(list, n, currentString, left+1, right);
            currentString.deleteCharAt(currentString.length() - 1);
        }

        if (right < left) {
            currentString.append(")");
            dfs(list, n, currentString, left, right+1);
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> res = generateParentheses(2);
        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue("For n = 2", Arrays.asList("(())", "()()").equals(res));

        res = generateParentheses(3);
        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue("For n = 3", Arrays.asList("((()))","(()())","(())()","()(())","()()()").equals(res));

        res = generateParentheses(1);
        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue("For n = 1", Arrays.asList("()").equals(res));
    }
}
