package com.algo.innov8r.basics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.swap;

public class QuickSort {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(5, 8, 3, 9, 4, 1, 7));
        sortList(arr);
        System.out.println(Arrays.toString(arr.toArray()));
    }

    public static List<Integer> sortList(List<Integer> unsortedList) {
        quickSort(unsortedList, 0, unsortedList.size()-1);
        return unsortedList;
    }

    static void quickSort(List<Integer> list, int s, int e) {
        if (e - s <= 0) {
            return;
        }

        int pivot= pivot(list, s, e);
        quickSort(list, s, pivot - 1);
        quickSort(list, pivot + 1, e);
    }

    static int pivot(List<Integer> list, int s, int e) {
        int left = s;
        int right = e - 1;
        int temp = list.get(e);
        while (left < right) {
            while (left < right && list.get(left) < temp) {
                left++;
            }

            while (left < right && list.get(right) > temp) {
                right--;
            }

            if (left == right) break;
            swap(list, left, right);
        }

        swap(list, right, e);

        return right;
    }

    private static void swap(List<Integer> list, int left, int right) {

        int temp = list.get(right);
        list.set(right, list.get(left));
        list.set(left, temp);
    }
}
