package com.algo.innov8r.basics;
import java.util.*;
public class MergeSort {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(5, 8, 3, 9, 4, 1, 7));
        ArrayList<Integer> a2 = merge_sort(arr);
        System.out.println(Arrays.toString(a2.toArray()));
    }

    static ArrayList<Integer> merge_sort(ArrayList<Integer> arr) {
        return helper(arr, 0, arr.size());
    }

    static ArrayList<Integer> helper(ArrayList<Integer> arr, int i, int j) {
        if (j - i <= 1) {
            return new ArrayList<>(arr.subList(i, j));
        }

        int mid = i + (j - i) / 2;
        ArrayList<Integer> left = helper(arr, i, mid);
        ArrayList<Integer> right = helper(arr, mid, j);
        ArrayList<Integer> merged = new ArrayList<>();
        int l = 0;
        int r = 0;
        while (l < left.size() || r < right.size()) {
            if (l == left.size()) {
                merged.add(right.get(r));
                r++;
            } else if (r == right.size()) {
                merged.add(left.get(l));
                l++;
            } else if (left.get(l) < right.get(r)) {
                merged.add(left.get(l));
                l++;
            } else {
                merged.add(right.get(r));
                r++;
            }
        }

        return merged;
    }

}