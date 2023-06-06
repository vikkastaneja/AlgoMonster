package com.algo.innov8r.binarysearch;

public class BinarySearchWithDuplicates {
    public static void main(String[] args) {
        int[]arr3 = {1,2,3,3,3,3,4,5};
        System.out.println(binarySearch(arr3, 3));

        int[]arr2 = {1, 3, 3, 3, 3, 6, 10, 10, 10, 100};
        System.out.println(binarySearch(arr2, 3));

        int[]arr1 = {2, 3, 5, 7, 11, 13, 17, 19};
        System.out.println(binarySearch(arr1, 6));
    }

    static int binarySearch(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        int last_index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == target) {
              last_index = mid;
              right = mid - 1;
            } else if (target < a[mid]) right = mid - 1;
            else left = mid + 1;
        }

        return last_index;
    }
}
