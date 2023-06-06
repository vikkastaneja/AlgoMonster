package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class BalancedBinaryTree {
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
        String test1 = "1 2 4 x 7 x x 5 x x 3 x 6 x x";
        BinaryTreeNode<Integer> root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        boolean res = isBalanced(root);
        Assert.assertEquals(true, res);

        test1 = "1 2 4 x 7 x x 5 x x 3 x 6 8 x x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = isBalanced(root);
        Assert.assertEquals(false, res);
//
        test1 = "1 2 3 x x 4 x 6 x x 5 x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = isBalanced(root);
        Assert.assertEquals(false, res);
//
        test1 = "1 2 3 x x 4 x 6 x x 5 x 7 x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = isBalanced(root);
        Assert.assertEquals(true, res);
//
        test1 = "1 2 3 x x 4 x x 5 6 x 7 x x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = isBalanced(root);
        Assert.assertEquals(false, res);
//
        test1 = "1 2 3 7 x x x 4 x x 5 6 x x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = isBalanced(root);
        Assert.assertEquals(true, res);

        test1 = "1 2 3 4 x x 4 x x 3 x x 2 x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = isBalanced(root);
        Assert.assertEquals(false, res);
        System.out.println("All tests passed!");
    }

    private static boolean isBalanced(BinaryTreeNode<Integer> root) {
        if (root == null) return true;
        return height(root) != -1;
    }

    private static int height(BinaryTreeNode<Integer> node) {
        if (node == null) return 0;
        int left = height(node.getLeft());
        int right = height(node.getRight());

        if (Math.abs(height(node.getRight()) - height(node.getLeft())) > 1) {
            return -1;
        }

        if (left == -1 || right == -1) {
            return -1;
        }

        return Math.max(height(node.getRight()), height(node.getLeft())) + 1;
    }
}
