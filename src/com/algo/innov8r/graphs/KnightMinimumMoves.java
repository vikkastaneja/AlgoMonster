package com.algo.innov8r.graphs;

import java.util.*;

import org.junit.Assert;

/**
 * On an infinitely large chessboard, a knight is located on [0, 0].
 * A knight can move in eight directions.
 * Given a destination coordinate [x, y], determine the minimum number of moves from [0, 0] to [x, y].
 */
public class KnightMinimumMoves {
    public static void main(String[] args) {
        Assert.assertEquals("Shortest path is wrong for (2, 1) ", 1, getKnightShortestPath(2, 1));
        Assert.assertEquals("Shortest path is wrong for (5, 5) ", 4, getKnightShortestPath(5, 5));
        Assert.assertEquals("Shortest path is wrong for (1, 1) ", 2, getKnightShortestPath(1, 1));
    }

    public static class Coordinate {
        int x, y;
        boolean visited;

        public Coordinate (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int getKnightShortestPath(int x, int y) {
        // Since the goal is to find out min out of an unbounded board, we will do a bfs and find out the path
        Coordinate start = new Coordinate(0, 0);
        start.visited = true;
        Queue<Coordinate> q = new LinkedList<>();
        q.add(start);
        int depth = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Coordinate current = q.remove();
                if (current.x == x && current.y == y) {
                    return depth;
                }

                List<Coordinate> neighbors = getNeighbors(current);
                for (Coordinate n : neighbors) {
                    if (n.visited) continue;
                    q.add(n);
                }
            }

            depth++;
        }

        return depth;
    }

    private static List<Coordinate> getNeighbors(Coordinate c) {
        List<Coordinate> neigh = new ArrayList<>();
        /*
        Knight can move in 8 possible directions (2 and 1 move combo):
        Up --> Left: x,y --> x-2, y-1
        Up --> Right: x,y --> x-2, y+1
        Left --> Up: x,y --> x-1, y-2
        Left --> Down: x,y --> x+1, y-2
        Down --> Left: x,y --> x+2, y-1
        Down --> Right: x,y --> x+2, y+1
        Right --> Up: x,y --> x-1, y+2
        Right --> Down: x,y --> x+1, y+2
         */
        int[] x_moves = {-2, -2, -1, 1, 2, 2, -1, 1};
        int[] y_moves = {-1, 1, -1, -1, -1, 1, 2, 2};

        for (int i = 0; i < x_moves.length; i++) {
            neigh.add(new Coordinate(c.x + x_moves[i], c.y + y_moves[i]));
        }

        return neigh;
    }
}
