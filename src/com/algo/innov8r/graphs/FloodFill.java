package com.algo.innov8r.graphs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

import org.junit.Assert;

// Runtime: O(num of rows * num of columns)
// Memory: O(num or rows * num of columns)
public class FloodFill {
    public static class Coordinate {
        int r,c;

        public Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static List<Coordinate> getNeighbours(List<List<Integer>> image, Coordinate coordinate, int value, int target, boolean[][]visited) {
        List<Coordinate> neighbours = new ArrayList<>();
        if (coordinate.r < 0 || coordinate.c < 0 || coordinate.r >= image.size() || coordinate.c >= image.get(0).size()) {
            return neighbours;
        }

        // Left
        if (coordinate.r > 0 && !visited[coordinate.r - 1][coordinate.c] && image.get(coordinate.r-1).get(coordinate.c) == value) {
            image.get(coordinate.r - 1).set(coordinate.c, target);
            neighbours.add(new Coordinate(coordinate.r - 1, coordinate.c));
        }

        // Right
        if (coordinate.r < image.size() - 1 && !visited[coordinate.r + 1][coordinate.c] && image.get(coordinate.r+1).get(coordinate.c) == value) {
            image.get(coordinate.r + 1).set(coordinate.c, target);
            neighbours.add(new Coordinate(coordinate.r + 1, coordinate.c));
        }

        // Top
        if (coordinate.c > 0 && !visited[coordinate.r][coordinate.c - 1] && image.get(coordinate.r).get(coordinate.c-1) == value) {
            image.get(coordinate.r).set(coordinate.c-1, target);
            neighbours.add(new Coordinate(coordinate.r, coordinate.c-1));
        }

        // Bottom
        if (coordinate.c < image.get(0).size() - 1 && !visited[coordinate.r][coordinate.c + 1] && image.get(coordinate.r).get(coordinate.c + 1) == value) {
            image.get(coordinate.r).set(coordinate.c + 1, target);
            neighbours.add(new Coordinate(coordinate.r, coordinate.c + 1));
        }

        return neighbours;
    }

    public static List<List<Integer>> floodFill(int r, int c, int replacement, List<List<Integer>> image) {
        // Logic:
        // 1. Get current pixel's number
        // 2. Add current pixel to queue
        // 3. Until the queue is empty
        // 3a. Remove front of the queue
        // 3b. Find neighbours that has same value and are not visited
        // 3c. Change value of those neighbours
        // 3d. Add those neighbours to the queue
        // 3e. Mark current pixel to visited

        int currentValue = image.get(r).get(c);
        Queue<Coordinate> queue = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        queue.add(new Coordinate(r, c));
        image.get(r).set(c, replacement);
        boolean[][] visited = new boolean[image.size()][image.get(0).size()];
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int len = queue.size();
            Coordinate current = queue.remove();
            visited[current.r][current.c] = true;
            List<Coordinate> neighbours = getNeighbours(image, current, currentValue, replacement, visited);
            queue.addAll(neighbours);
        }

        return image;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        // Test 1
        String[] inputs = {"2","2","9","5","0 1 3 4 1","3 8 8 3 3","6 7 8 8 3","12 2 8 9 1","12 3 1 3 2"};
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        for (String s : inputs) {
            pw.println(s);
        }
        InputStream ip = new ByteArrayInputStream(sw.toString().getBytes());
        Scanner scanner = new Scanner(ip);
        int r = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());
        int replacement = Integer.parseInt(scanner.nextLine());
        int imageLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> image = new ArrayList<>();
        for (int i = 0; i < imageLength; i++) {
            image.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<List<Integer>> res = floodFill(r, c, replacement, image);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(0, 1, 3, 4, 1),Arrays.asList(3, 9, 9, 3, 3),Arrays.asList(6, 7, 9, 9, 3),Arrays.asList(12, 2, 9, 9, 1),Arrays.asList(12, 3, 1, 3, 2));

        Assert.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }

        // Test 2
        String[] inputs1 = {"1","1","9","4","0 1 6 4","2 3 3 5","3 3 3 3","6 4 3 4"};
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        for (String s : inputs1) {
            pw.println(s);
        }
        ip = new ByteArrayInputStream(sw.toString().getBytes());
        scanner = new Scanner(ip);
        r = Integer.parseInt(scanner.nextLine());
        c = Integer.parseInt(scanner.nextLine());
        replacement = Integer.parseInt(scanner.nextLine());
        imageLength = Integer.parseInt(scanner.nextLine());
        image = new ArrayList<>();
        for (int i = 0; i < imageLength; i++) {
            image.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        res = floodFill(r, c, replacement, image);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        expected = Arrays.asList(Arrays.asList(0, 1, 6, 4),Arrays.asList(2, 9, 9, 5),Arrays.asList(9, 9, 9, 9),Arrays.asList(6, 4, 9, 4));

        Assert.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }
    }
}
