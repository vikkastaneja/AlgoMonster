package com.algo.innov8r.binarysearch;

public class SquareRoot {
    public static void main(String[] args) {
        System.out.println(squareRoot(16));
        System.out.println(squareRoot(8));
        System.out.println(squareRoot(9));
        System.out.println(squareRoot(100));
        System.out.println(squareRoot(1));
        System.out.println(squareRoot(0));
        System.out.println(squareRoot(10000));
    }

    static int squareRoot(int n) {
        if (n == 0) return n;
        int left = 1;
        int right = n;
        int diff = Integer.MAX_VALUE;
        int last = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid * mid == n) {
                return mid;
            } else if (mid * mid < n && n - (mid * mid) < diff) {
                diff = n - (mid * mid);
                last = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return last;
    }
}
