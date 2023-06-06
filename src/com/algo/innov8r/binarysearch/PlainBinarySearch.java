package com.algo.innov8r.binarysearch;

public class PlainBinarySearch {
    public static void main(String[] args) {
        int[] arr= {1,2,3,4,5,6,7,8,9};
        System.out.println(binarySearch(arr, 7));
        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 10));
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 9));
        System.out.println(binarySearch(arr, 0));
        int[]arr1 = {};
        System.out.println(binarySearch(arr1, 0));
        int []arr2 = {1};
        System.out.println(binarySearch(arr2, 1));

        int[]arr3 = {1,2,3,3,4,5};
        System.out.println(binarySearch(arr3, 3));
    }

    static int binarySearch(int[]arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left +  (right - left) / 2;
            if (arr[mid] == target) return mid;

            if (target < arr[mid]) right = mid - 1;
            else left = mid + 1;
        }

        return -1;
    }
}
