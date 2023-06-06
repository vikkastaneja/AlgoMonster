package com.algo.innov8r.binarysearch;

public class BinarySearchFirstIndexOfBooleanArray {
    public static void main(String[] args) {
        boolean[] arr = {false, false, true, true};
        System.out.println(binarySearchBooleanArray(arr));

        boolean[] arr1 = {false, false, false, false};
        System.out.println(binarySearchBooleanArray(arr1));

        boolean[] arr2 = {true, true, true, true};
        System.out.println(binarySearchBooleanArray(arr2));
    }

    static int binarySearchBooleanArray(boolean[]a) {
        int left = 0;
        int right = a.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == true) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return index;
    }
}
