package com.algo.innov8r.twopointers;

import java.util.*;
import java.util.stream.Collectors;

import com.algo.innov8r.common.Assertion;

/**
 * Naive approach is for each character in original array, if the element is in the check, then validate each element and it's frequency.
 * A slightly better approach is to remove the last element from the list and add the next element to the list and then perform comparison. 
 */
public class AllAnagramsInString {
    public static List<Integer> findAllAnagrams(String original, String check) {
        int olen = original.length();
        int clen = check.length();
        List<Integer> list = new ArrayList<>();
        if (clen > olen) return list;

        // assuming that the characters are going to be between 'a' and 'z', 
        // Keep two arrays as frequency tables
        int[] cfreq = new int[26];
        int[] ofreq = new int[26];
        for (int i = 0; i < clen; i++) {
            cfreq[check.charAt(i) - 'a']++;
            ofreq[original.charAt(i) - 'a']++;
        }

        if (Arrays.equals(ofreq, cfreq)) {
            list.add(0);
        }

        for (int i = 1; i <= olen - clen; i++) {
            ofreq[original.charAt(i-1) - 'a']--;
            ofreq[original.charAt(i + clen - 1) - 'a']++;
            if (Arrays.equals(ofreq, cfreq)) {
                list.add(i);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        String original = "cbaebabacd";
        String check = "abc";
        List<Integer> res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        Assertion.assertTrue(res.equals(Arrays.asList(0,6)));

        original = "abab";
        check = "ab";
        res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        Assertion.assertTrue(res.equals(Arrays.asList(0,1,2)));

        original = "nabanabannaabbaanana";
        check = "banana";
        res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        Assertion.assertTrue(res.equals(Arrays.asList(0,3,5,6,7,13)));

        original = "wubudubuwubuthattrue";
        check = "ubutu";
        res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        Assertion.assertTrue(res.equals(Arrays.asList()));

        original = "act";
        check = "cact";
        res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        Assertion.assertTrue(res.equals(Arrays.asList()));

        original = "thequickbrownfoxjumpsoverthelazydog";
        check = "thelazydogjumpsoverthequickbrownfox";
        res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        Assertion.assertTrue(res.equals(Arrays.asList(0)));

        original = "sussusamongus".repeat(10);
        check = "sumosangus";
        res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        Assertion.assertTrue(res.equals(Arrays.asList(1, 2, 3, 4, 5, 6, 14, 15, 16, 17, 18, 19, 27, 28, 29, 30, 31, 32, 40, 41, 42, 43, 44, 45, 53, 54, 55, 56, 57, 58, 66, 67, 68, 69, 70, 71, 79, 80, 81, 82, 83, 84, 92, 93, 94, 95, 96, 97, 105, 106, 107, 108, 109, 110, 118, 119, 120)));
    }
}
