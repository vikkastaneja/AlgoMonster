package com.algo.innov8r.breadthfirstsearch;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Assert;

public class BinaryTreeLevelOrderTraversal {
    public static class Node<T> {
        public T val;
        public Node<T> left;
        public Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<List<Integer>> levelOrderTraversal(Node<Integer> root) {
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++){
                Node<Integer> current = queue.remove();
                temp.add(current.val);
                if (current.left != null) {
                    queue.add(current.left);
                }

                if (current.right != null) {
                    queue.add(current.right);
                }

            }

            list.add(temp);
        }

        return list;
    }

    // this function builds a tree from input; you don't have to modify it
    // learn more about how trees are encoded in https://algo.monster/problems/serializing_tree
    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        if (val.equals("x")) return null;
        Node<T> left = buildTree(iter, f);
        Node<T> right = buildTree(iter, f);
        return new Node<T>(f.apply(val), left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Node<Integer> root = buildTree(splitWords("1 2 4 x 7 x x 5 x x 3 x 6 x x").iterator(), Integer::parseInt);
        List<List<Integer>> res = levelOrderTraversal(root);

        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1), Arrays.asList(2,3), Arrays.asList(4,5,6), Arrays.asList(7));

        Assert.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }

        root = buildTree(splitWords("1 x x").iterator(), Integer::parseInt);
        res = levelOrderTraversal(root);

        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        expected = Arrays.asList(Arrays.asList(1));

        Assert.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }
    }
}
