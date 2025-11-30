package com.algo.innov8r.heap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

import com.algo.innov8r.common.Assertion;

public class KClosestPointsFromOrigin {
    public static List<List<Integer>> kClosestPoints(List<List<Integer>> points, int k) {
        // Naive way is to find out is to get the distances calculated from origin for each point and then sort them out and find top k points. Order of this is nlogn.
        // Another way of doing this is to have a heap where the sorting mechanism is based on the distance from the origin. 
        PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> l1, List<Integer> l2){
                return (l1.get(0) * l1.get(0) + l1.get(1) * l1.get(1)) - (l2.get(0) * l2.get(0) + l2.get(1) * l2.get(1));
            }
        });

        for (List<Integer> list : points) {
            pq.add(list);
        }

        List<List<Integer>> list = new ArrayList<>();

        while (k-- > 0 && !pq.isEmpty()) {
            list.add(pq.remove());
        }
        return list;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {

        // Test 1
        String[] inputs = {"4","4 4","2 4","8 1","3 -5","2"};
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        for (String s : inputs) {
            pw.println(s);
        }
        InputStream ip = new ByteArrayInputStream(sw.toString().getBytes());
        Scanner scanner = new Scanner(ip);
        int pointsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> points = new ArrayList<>();
        for (int i = 0; i < pointsLength; i++) {
            points.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        List<List<Integer>> res = kClosestPoints(points, k);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(2,4), Arrays.asList(4,4));

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }

        // Test 2
        String[] inputs2 = {"2", "1 3", "-2 2","1"};
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        for (String s : inputs2) {
            pw.println(s);
        }
        ip = new ByteArrayInputStream(sw.toString().getBytes());
        scanner = new Scanner(ip);
        pointsLength = Integer.parseInt(scanner.nextLine());
        points = new ArrayList<>();
        for (int i = 0; i < pointsLength; i++) {
            points.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        res = kClosestPoints(points, k);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        expected = Arrays.asList(Arrays.asList(-2,2));

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }

        // Test 3
        String[] inputs3 = {"3", "3 3", "2 2","1 1", "1"};
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        for (String s : inputs3) {
            pw.println(s);
        }
        ip = new ByteArrayInputStream(sw.toString().getBytes());
        scanner = new Scanner(ip);
        pointsLength = Integer.parseInt(scanner.nextLine());
        points = new ArrayList<>();
        for (int i = 0; i < pointsLength; i++) {
            points.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        res = kClosestPoints(points, k);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        expected = Arrays.asList(Arrays.asList(1,1));

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }
    }
}
