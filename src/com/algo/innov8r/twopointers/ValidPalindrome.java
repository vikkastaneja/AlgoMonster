package com.algo.innov8r.twopointers;

import com.algo.innov8r.common.Assertion;

public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        // Convert it to lower character string.
        // Start from both ends and match the characters
        char[] ch = s.toLowerCase().toCharArray();
        int i = 0;
        int j = ch.length - 1;
        while (i <= j) {
            if (ch[i] < 'a' || ch[i] > 'z') i++;
            if (ch[j] < 'a' || ch[j] > 'z') j--;

            if (i <= j && ch[i] != ch[j]) return false;
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "Do geese see God?";
        Assertion.assertTrue(isPalindrome(s));

        s = "Was it a car or a cat I saw?";
        Assertion.assertTrue(isPalindrome(s));

        s = "A brown fox jumping over";
        Assertion.assertFalse(isPalindrome(s));
    }
}
