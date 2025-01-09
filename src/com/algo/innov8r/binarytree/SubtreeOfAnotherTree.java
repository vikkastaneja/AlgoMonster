package com.algo.innov8r.binarytree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Given two binary trees root and subRoot, determine if subRoot is a subtree of root. A subtree of a binary tree is a tree that consists of a node in the tree and all of its descendants. The tree root is a subtree of itself.
 */
public class SubtreeOfAnotherTree {
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

    public static boolean subtreeOfAnotherTree(Node<Integer> root, Node<Integer> subRoot) {
        // if both nodes are null, return true
        if (root == null && subRoot == null) {
            return true;
        }

        // If root is null, return false
        if (root == null) {
            return false;
        }

        // If subRoot is null, return true
        if (subRoot == null) {
            return true;
        }

        // If the current nodes are same, check equality of the remaining trees in separate function
        if (root.val == subRoot.val) {
            if (checkEquality(root, subRoot)) {
                return true;
            }
        }

        // If the current nodes are not same, iterate in the left subtree and then right subtree of root keeping subroot same
        return subtreeOfAnotherTree(root.left, subRoot) || subtreeOfAnotherTree(root.right, subRoot);
    }

    private static boolean checkEquality(Node<Integer> root, Node<Integer> subRoot) {
        // If both nodes are null, return true
        if (root == null && subRoot == null) {
            return true;
        }

        // If either one is null, return false
        if (root == null || subRoot == null) {
            return false;
        }

        // If both are not equal, return false
        if (root.val != subRoot.val) {
            return false;
        }

        // Recursively check left and right subtrees
        return checkEquality(root.left, subRoot.left) && checkEquality(root.right, subRoot.right);
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
        Node<Integer> root = buildTree(splitWords("3 4 1 x x 2 x x 5 x x").iterator(), Integer::parseInt);
        Node<Integer> subRoot = buildTree(splitWords("4 1 x x 2 x x").iterator(), Integer::parseInt);
        boolean res = subtreeOfAnotherTree(root, subRoot);
        System.out.println(res);
        assertTrue(res);

        root = buildTree(splitWords("3 4 1 x x 2 0 x x x 5 x x").iterator(), Integer::parseInt);
        subRoot = buildTree(splitWords("4 1 x x 2 x x").iterator(), Integer::parseInt);
        res = subtreeOfAnotherTree(root, subRoot);
        System.out.println(res);
        assertFalse(res);
    }
}