package com.algo.innov8r.graphs;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.Assert;

import java.io.InputStream;

// Runtime: O(n+m) where n is number of edges and m is number of nodes (size of larger array)
// Memory: O(number of nodes) - linear each node in the queue
public class ShortestPath {
    public static int shortestPath(List<List<Integer>> graph, int a, int b) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        int len = 0;
        boolean[] visited = new boolean[graph.size()];
        visited[a] = true;
        while(!queue.isEmpty()) {
            int queue_len = queue.size();
            len++;
            for (int i = 0; i < queue_len; i++) {
                Integer temp = queue.remove();
                for (int t : graph.get(temp)) {
                    if (t == b) {
                        return len;
                    }

                    if (visited[t]) {
                        continue;
                    }

                    visited[t] = true;
                    queue.add(t);
                }
            }
        }

        return 0;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        // Test 1
        String[] inputs = {"4", "1 2", "0 2 3", "0 1", "1", "0", "3"};
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        for (String s : inputs) {
            pw.println(s);
        }
        InputStream ip = new ByteArrayInputStream(sw.toString().getBytes());
        Scanner scanner = new Scanner(ip);
        int graphLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < graphLength; i++) {
            graph.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = shortestPath(graph, a, b);
        System.out.println(res);
        Assert.assertEquals(2, res);

        // Test 2
        String[] inputs1 = {"10", "1 4","0 2","1 3","2 4 5","0 3","3 6 9","5 8","8 9","6 7","5 7","0","7"};
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        for (String s : inputs1) {
            pw.println(s);
        }
        ip = new ByteArrayInputStream(sw.toString().getBytes());
        scanner = new Scanner(ip);
        graphLength = Integer.parseInt(scanner.nextLine());
        graph = new ArrayList<>();
        for (int i = 0; i < graphLength; i++) {
            graph.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        a = Integer.parseInt(scanner.nextLine());
        b = Integer.parseInt(scanner.nextLine());
        scanner.close();
        res = shortestPath(graph, a, b);
        System.out.println(res);
        Assert.assertEquals(5, res);

        // Test 3
        String[] inputs2 = {"3", "1 2","0","0","1","2"};
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        for (String s : inputs2) {
            pw.println(s);
        }
        ip = new ByteArrayInputStream(sw.toString().getBytes());
        scanner = new Scanner(ip);
        graphLength = Integer.parseInt(scanner.nextLine());
        graph = new ArrayList<>();
        for (int i = 0; i < graphLength; i++) {
            graph.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        a = Integer.parseInt(scanner.nextLine());
        b = Integer.parseInt(scanner.nextLine());
        scanner.close();
        res = shortestPath(graph, a, b);
        System.out.println(res);
        Assert.assertEquals(2, res);

        // Test 4
        String[] inputs3 = {"2", "1","0","1","0"};
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        for (String s : inputs3) {
            pw.println(s);
        }
        ip = new ByteArrayInputStream(sw.toString().getBytes());
        scanner = new Scanner(ip);
        graphLength = Integer.parseInt(scanner.nextLine());
        graph = new ArrayList<>();
        for (int i = 0; i < graphLength; i++) {
            graph.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        a = Integer.parseInt(scanner.nextLine());
        b = Integer.parseInt(scanner.nextLine());
        scanner.close();
        res = shortestPath(graph, a, b);
        System.out.println(res);
        Assert.assertEquals(1, res);
    }
}
