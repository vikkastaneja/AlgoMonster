package com.algo.innov8r.binarysearch;

public class FindElementLargerEqualsTarget {
    // Given an array of integers sorted in increasing order and a target,
    // find the index of the first element in the array that is larger than or equal to the target.
    // Assume that it is guaranteed to find a satisfying number.
    // Array may contain duplicates
    public static void main(String[] args) {
        int[]arr3 = {1,3, 4,5};
        System.out.println(binarySearchMinDiff(arr3, 2));

        int[]arr1 = {1, 3, 3, 5, 8, 8, 10};
        System.out.println(binarySearchMinDiff(arr1, 2));

        int[]arr2 = {2, 3, 5, 7, 11, 13, 17, 19};
        System.out.println(binarySearchMinDiff(arr2, 6));
    }

    // Apply template
    static int binarySearchMinDiff(int[] a, int target){
        int left = 0, right = a.length - 1;
        int last_index = -1;
        int min = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == target) return mid;
            else if (/*condition*/ a[mid] - target > 0 && a[mid] - target < min) {
                last_index = mid;
                min = a[mid] - target;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return last_index;
    }
}
