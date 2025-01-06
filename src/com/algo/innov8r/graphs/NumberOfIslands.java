package com.algo.innov8r.graphs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;


// Runtime: O(m*n)
// Memory: O(m*n)
public class NumberOfIslands {
    public static class Coordinate {
        int r, c;
        public Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static int countNumberOfIslands(List<List<Integer>> grid) {
        // Steps:
        // 1. Create a boolean 2D array of same size as grid
        // 2. For each element of the grid, perform a traversal (BFS or DFS) such that:
        // 2a.  If the current element is not visited, mark as visited.
        // 2b.  Increment the counter of number of island as this will be one of the island's element
        // 2c.  Mark all the neighbours as visited as well until all neighbours are exhausted
        int counter = 0;
        boolean[][] visited = new boolean[grid.size()][grid.get(0).size()];
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (!visited[i][j] && grid.get(i).get(j) == 1) {
                    bfs(grid, new Coordinate(i, j), visited);
                    counter++;
                }
            }
        }

        return counter;
    }

    private static void bfs(List<List<Integer>> grid, Coordinate coordinate, boolean[][]visited) {
        Queue<Coordinate> queue = new LinkedList<>();
        // visited[coordinate.r][coordinate.c] = true;
        queue.add(coordinate);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Coordinate coord = queue.remove();
                int r = coord.r;
                int c = coord.c;
                visited[r][c] = true;
                //left
                if (r > 0 && grid.get(r-1).get(c) == 1 && !visited[r-1][c]) {
                    queue.add(new Coordinate(r-1, c));
                    visited[r-1][c] = true;

                }

                // right
                if (r < grid.size()-1 && grid.get(r+1).get(c) == 1 && !visited[r+1][c]) {
                    queue.add(new Coordinate(r+1, c));
                    visited[r+1][c] = true;
                }

                // Top
                if ( c > 0 && grid.get(r).get(c-1) == 1 && !visited[r][c-1]) {
                    queue.add(new Coordinate(r, c-1));
                    visited[r][c-1] = true;
                }

                // Bottom
                if (c < grid.get(0).size() - 1 && grid.get(r).get(c+1) == 1 && !visited[r][c+1]) {
                    queue.add(new Coordinate(r, c + 1));
                    visited[r][c+1] = true;
                }
            }
        }
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        String[] inputs = {"6", "1 1 1 0 0 0", "1 1 1 1 0 0", "1 1 1 0 0 0", "0 1 0 0 0 0", "0 0 0 0 1 0", "0 0 0 0 0 0"};
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        for (String s : inputs) {
            pw.println(s);
        }
        InputStream ip = new ByteArrayInputStream(sw.toString().getBytes());
        Scanner scanner = new Scanner(ip);
        int gridLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < gridLength; i++) {
            grid.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = countNumberOfIslands(grid);
        System.out.println(res);
    }
}
