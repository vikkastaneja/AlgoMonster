package com.algo.innov8r.backtracking;

import static com.algo.innov8r.common.Assertion.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class TernaryTreePaths {
    /**
     * Given a ternary tree (each node of the tree can have up to three children), find all the root-to-leaf paths.
     */
    public static class Node<T> {
        public T val;
        public List<Node<T>> children;

        public Node(T val) {
            this(val, new ArrayList<>());
        }

        public Node(T val, List<Node<T>> children) {
            this.val = val;
            this.children = children;
        }
    }

    public static List<String> ternaryTreePaths(Node<Integer> root) {
        // WRITE YOUR BRILLIANT CODE HERE
        // if (root == null) return new ArrayList<>();
        List<String> rl = new ArrayList<>();
        if (root.children.size() == 0) {
            rl.add(String.valueOf(root.val));
            return rl;
        }

        for(Node node : root.children) {
            List<String> temp = ternaryTreePaths(node);
            for (String n : temp) {
                rl.add(String.valueOf(root.val) + "->" + n);
            }
        }

        return rl;
    }

    // this function builds a tree from input; you don't have to modify it
    // learn more about how trees are encoded in https://algo.monster/problems/serializing_tree
    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        int num = Integer.parseInt(iter.next());
        ArrayList<Node<T>> children = new ArrayList<>();
        for (int i = 0; i < num; i++)
            children.add(buildTree(iter, f));
        return new Node<T>(f.apply(val), children);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        String value = "1 3 2 1 5 0 3 0 4 0";
        Node<Integer> root = buildTree(splitWords(value).iterator(), Integer::parseInt);
        // scanner.close();
        List<String> res = ternaryTreePaths(root);
        for (String line : res) {
            System.out.println(line);
        }

        System.out.println("#######################");
        List<String> expected = Arrays.asList("1->2->5", "1->3", "1->4");
        assertTrue(compareList(res, expected));

        value = "1 3 2 3 3 0 4 0 7 0 5 0 6 0";
        root = buildTree(splitWords(value).iterator(), Integer::parseInt);
        // scanner.close();
        res = ternaryTreePaths(root);
        for (String line : res) {
            System.out.println(line);
        }

        System.out.println("#######################");
        expected = Arrays.asList("1->2->3", "1->2->4", "1->2->7", "1->5", "1->6");
        assertTrue(compareList(res, expected));

        value = "1 3 2 1 3 0 4 0 6 0";
        root = buildTree(splitWords(value).iterator(), Integer::parseInt);
        // scanner.close();
        res = ternaryTreePaths(root);
        for (String line : res) {
            System.out.println(line);
        }

        System.out.println("#######################");
        expected = Arrays.asList("1->2->3", "1->4", "1->6");
        assertTrue(compareList(res, expected));

    }

    private static boolean compareList(List<String> l1, List<String> l2) {
        return l1.equals(l2);
    }
}
