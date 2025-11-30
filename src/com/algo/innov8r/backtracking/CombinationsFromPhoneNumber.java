package com.algo.innov8r.backtracking;

import com.algo.innov8r.common.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationsFromPhoneNumber {
    public static List<String> letterCombinationsOfPhoneNumber(String digits) {
        List<String> returnList = new ArrayList<>();
        Map<Character, String> map = new HashMap<>() {{
            put('1', "");
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        dfs(map, "", 0, digits, returnList);
        return returnList;
    }

    private static void dfs(Map<Character, String> map, String current, int currentIndex, String digits, List<String> list) {
        if (currentIndex == digits.length()) {
            list.add(current);
            return;
        }

        for (char c : map.get(digits.charAt(currentIndex)).toCharArray()) {
            dfs(map, current + String.valueOf(c), currentIndex + 1, digits, list);
        }
    }

    public static void main(String[] args) {
        String digits = "56";
        List<String> res = letterCombinationsOfPhoneNumber(digits);
        System.out.println(String.join(" ", res));

        List<String> ret = Arrays.asList("jm","jn","jo","km","kn","ko","lm","ln","lo");
        Assertion.assertTrue(ret.equals(res));

        res = letterCombinationsOfPhoneNumber("23");
        System.out.println(String.join(" ", res));

        ret = Arrays.asList("ad","ae","af","bd","be","bf","cd","ce","cf");
        Assertion.assertTrue(ret.equals(res));

        res = letterCombinationsOfPhoneNumber("");
        System.out.println(String.join(" ", res));

        ret = Arrays.asList("");
        Assertion.assertTrue(ret.equals(res));

        res = letterCombinationsOfPhoneNumber("235");
        System.out.println(String.join(" ", res));

        ret = Arrays.asList("adj","adk","adl","aej","aek","ael","afj","afk","afl","bdj","bdk","bdl","bej","bek","bel","bfj","bfk","bfl","cdj","cdk","cdl","cej","cek","cel","cfj","cfk","cfl");
        Assertion.assertTrue(ret.equals(res));
    }
}
