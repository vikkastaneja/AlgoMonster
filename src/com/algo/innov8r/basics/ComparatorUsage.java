package com.algo.innov8r.basics;
import java.util.Arrays;
import java.util.Comparator;

public class ComparatorUsage {

    public class MySample {
        public char c;
        public MySample(char c1) {
            c =c1;
        }

        public String toString() {
            return String.valueOf(c);
        }
    }
    public static void main(String[] args) {
        Integer[]arr = {91,3,1,5,11,422,64,22};
        Comparator<Integer> comp = (Integer a, Integer b) -> {
            return b - a;
        };
        Arrays.sort(arr, comp);

        System.out.println(Arrays.toString(arr));
        ComparatorUsage usage = new ComparatorUsage();
        usage.comparatorUsageForObjects();
    }

    public void comparatorUsageForObjects() {
        Comparator<MySample> comp1 = (MySample m1, MySample m2 ) -> {
            return m2.c - m1.c;
        };


        MySample []arr2 = {new MySample('b'), new MySample('k'), new MySample('a'), new MySample('d')};
        Arrays.sort(arr2, comp1);
        System.out.println(Arrays.toString(arr2));

    }
}
