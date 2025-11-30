package com.algo.innov8r.backtracking;

import java.util.*;

import com.algo.innov8r.common.Assertion;

public class GenerateAllPermutations {
    public static List<String> permutations(String letters) {
        List<String> list = new ArrayList<>();
        dfs(letters.toCharArray(), list, 0, new StringBuilder(), new boolean[letters.length()]);
        return list;
    }

    private static void dfs(char[] letters, List<String> list, int currentIndex, StringBuilder sb, boolean[]visited) {
        if (sb.length() == letters.length) {
            list.add(new String(sb));
            return;
        }

        for (int i = 0; i < letters.length; i++) {
            if(visited[i]) {
                continue;
            }
            sb.append(letters[i]);
            visited[i] = true;
            dfs(letters, list, currentIndex + 1, sb, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[i]=false;
        }
    }

    public static void main(String[] args) {
       List<String> res = permutations("ab");
        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue(res.equals(Arrays.asList("ab", "ba")));

        res = permutations("abc");
        Collections.sort(res);
        for (String line : res) {
            System.out.println(line);
        }

        Assertion.assertTrue(res.equals(Arrays.asList("abc","acb","bac","bca","cab","cba")));
    }
}
