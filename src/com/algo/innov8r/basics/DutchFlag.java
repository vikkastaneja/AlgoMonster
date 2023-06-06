package com.algo.innov8r.basics;
import java.util.ArrayList;
import java.util.Arrays;

public class DutchFlag {
    public static void main(String[] args) {

        ArrayList<Character> arr = new ArrayList<Character>(Arrays.asList('G', 'B', 'G', 'G', 'R', 'B', 'R', 'G'));
        ArrayList<Character> a2 = dutch_flag_sort(arr);
        System.out.println(Arrays.toString(a2.toArray()));
    }

    static ArrayList<Character> dutch_flag_sort(ArrayList<Character> balls) {
        int r = 0;
        int b = balls.size() - 1;
        int current = 0;
        while (current <= b) {
            char c = balls.get(current);
            if (c == 'R') {
                char temp = balls.get(r);
                balls.set(r, c);
                balls.set(current, temp);
                r++;
                current++;
            } else if (c == 'B') {
                char temp = balls.get(b);
                balls.set(b, c);
                balls.set(current, temp);
                b--;
            } else {
                current++;
            }
        }

        return balls;
    }
}
