package com.algo.innov8r.binarysearch;

public class MinInRotatedSortedArray {

    public static void main(String[]args) {
        int[]arr1 = {30, 40, 50, 10, 20};
        System.out.println(findMinRotated(arr1));
        int[]arr2 = {3, 5, 7, 11, 13, 17, 19, 2};
        System.out.println(findMinRotated(arr2));
        int[]arr3 = {8, 9, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(findMinRotated(arr3));
    }

    static int findMinRotated(int[]a) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] <= a[right]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
