package com.algo.innov8r.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicates {
    public static int moveDuplicates(List<Integer> arr) {
        // i: position for exchange
        // j: to be processed
        // [0, 1, 2]
        //        i
        //        j
        int i = 0, j = 0;

        while (j < arr.size()) {
            while (j < arr.size() && arr.get(i) == arr.get(j))
                j++;

            i++;
            if (j < arr.size())
                arr.set(i, arr.get(j));
        }

        return i;
    }


    public static int removeDuplicates(List<Integer> arr) {
        int i = 0, j = 1;

        while (j < arr.size()) {
            while (j < arr.size() && arr.get(i) == arr.get(j)) {
                arr.remove(j);
            }

            i++;
            j++;
        }

        return arr.size();
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1,1,2,2,2,3,3,3,3,3,3);
        System.out.println(moveDuplicates(arr));

        arr = Arrays.asList(1,2,3,4,5,6,7,8);
        System.out.println(moveDuplicates(arr));

        arr = Arrays.asList(1,2,3,3,3,3,3,3,3);
        System.out.println(moveDuplicates(arr));

        arr = Arrays.asList(1);
        System.out.println(moveDuplicates(arr));

        arr = Arrays.asList(1,1,1,1,1,1,1,1);
        System.out.println(moveDuplicates(arr));

        arr = new ArrayList<>();
        arr.add(1);
        arr.add(1);
        arr.add(2);
        System.out.println(removeDuplicates(arr));
    }
}
