package com.algo.innov8r.breadthfirstsearch;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


import org.junit.Assert;
/**
 * Given a binary tree, return the rightmost node of each level.
 * It should be noted that at each level there can only be at most one rightmost node.
 * To solve the problem, the approach is to trace the tree breadth-wise and instead of going from left, 
 * go through right and pickup first element at each level.
 */
public class BinaryTreeRightSideView {
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

    public static List<Integer> binaryTreeRightSideView(Node<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<Node<Integer>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int len = q.size();
            ans.add(q.peek().val);
            for (int i = 0; i < len; i++) {
                Node<Integer> curr = q.remove();
                if (curr.right != null) q.add(curr.right);
                if (curr.left != null) q.add(curr.left);
            }
        }

        return ans;
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
        List<Integer> res = binaryTreeRightSideView(root);

        List<Integer> expected = Arrays.asList(1, 3, 6, 7);
        Assert.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }

        root = buildTree(splitWords("0 x x").iterator(), Integer::parseInt);
        res = binaryTreeRightSideView(root);

        expected = Arrays.asList(0);
        Assert.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }
    }
}