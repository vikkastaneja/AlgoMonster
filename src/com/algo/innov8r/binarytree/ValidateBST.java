package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class ValidateBST {

    public static boolean validBst(BinaryTreeNode<Integer> root) {
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean helper (BinaryTreeNode<Integer> node, int leftMax, int rightMin) {
        if (node == null) return true;

        if (node.getValue() < leftMax || node.getValue() > rightMin) return false;

        return helper(node.getLeft(), leftMax, node.getValue()) && helper(node.getRight(), node.getValue(), rightMin);
    }


    // this function builds a tree from input; you don't have to modify it
    // learn more about how trees are encoded in https://algo.monster/problems/serializing_tree
    public static <T> BinaryTreeNode<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        if (val.equals("x")) return null;
        BinaryTreeNode<T> left = buildTree(iter, f);
        BinaryTreeNode<T> right = buildTree(iter, f);
        return new BinaryTreeNode<T>(f.apply(val), left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        String val = "6 4 3 x x 5 x x 8 x x";
        BinaryTreeNode<Integer> root = buildTree(splitWords(val).iterator(), Integer::parseInt);
        Assert.assertTrue(validBst(root));

        val = "6 4 3 x x 8 x x 8 x x";
        root = buildTree(splitWords(val).iterator(), Integer::parseInt);
        Assert.assertFalse(validBst(root));

        val = "1 2 x x 3 x x";
        root = buildTree(splitWords(val).iterator(), Integer::parseInt);
        Assert.assertFalse(validBst(root));

        val = "x";
        root = buildTree(splitWords(val).iterator(), Integer::parseInt);
        Assert.assertTrue(validBst(root));

        val = "3 2 1 x x x 4 x 5 x x";
        root = buildTree(splitWords(val).iterator(), Integer::parseInt);
        Assert.assertTrue(validBst(root));

        val = "6 5 x 4 x x x";
        root = buildTree(splitWords(val).iterator(), Integer::parseInt);
        Assert.assertFalse(validBst(root));
    }
}
