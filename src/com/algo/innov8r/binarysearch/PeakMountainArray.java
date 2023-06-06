package com.algo.innov8r.binarysearch;

public class PeakMountainArray {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3,2,1,0};
        System.out.println(peakOfMountainArray(arr));

        int[] arr1 = {0,1,2,3,2};
        System.out.println(peakOfMountainArray(arr1));

        int[] arr2 = {2,3,2,1,0};
        System.out.println(peakOfMountainArray(arr2));

        int[] arr3 = {2,3,2};
        System.out.println(peakOfMountainArray(arr3));

    }

    static int peakOfMountainArray(int[]a) {
        int left = 0;
        int right = a.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == a.length - 1 || a[mid] > a[mid + 1]) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return index;
    }
}
