package com.algo.innov8r.binarysearch;

public class ArraySplitForMin {
    public static void main(String[] args) {
//        int [] arr = {7,2,5,10,8};
//        System.out.println(partitionMin(arr, 2));
//
//        int[] arr1 = {2,3,5,7};
//        System.out.println(partitionMin(arr1, 3));

        int[] arr2 = {1, 1, 1, 1, 1, 1};
        System.out.println(partitionMin(arr2, 6));
    }

    static int partitionMin(int[]a, int parts) {
        int left = Integer.MIN_VALUE; // left --> max value in the array
        int right = 0; // sum of the array
        for (int i : a) {
            left = Math.max(left, i);
            right += i;
        }

        int min = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Condition to find if mid target can be reached for assignment for parts
            if (canBeSplit(a, parts, mid)) {
                min = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return min;
    }

    private static boolean canBeSplit(int[]a, int parts, int mid) {
        int sum = 0;
        int current_part = 1;
        for (int current : a) {
            if (sum + current > mid) {
                sum = current;
                current_part ++;
            } else {
                sum += current;
            }
        }

        return current_part <= parts;
    }
}
