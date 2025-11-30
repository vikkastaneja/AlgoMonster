package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;
import com.algo.innov8r.common.Assertion;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class VisibleTreeNode {
//    public static void main(String[]args) {
//        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5, null, null);
//        root.setLeft(new BinaryTreeNode<>(4, new BinaryTreeNode<>(3, null, null), new BinaryTreeNode(8,null, null)));
//        root.setRight(new BinaryTreeNode<>(6, null, null));
//
////        System.out.println(visibleTreeNode(root));
//
//        total = 0;
//        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(5, null, null);
//        root1.setLeft(new BinaryTreeNode<>(8, new BinaryTreeNode(3, null, null), new BinaryTreeNode(8, null, null)));
//        root.setRight(new BinaryTreeNode<>(6, null, null));
//
//        System.out.println(visibleTreeNode(root1));
//
//    }

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
//        Scanner scanner = new Scanner(System.in);
        String test1 = "5 8 3 x x 9 x 7 8 x x x 6 x x";
        BinaryTreeNode<Integer> root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
//        scanner.close();
        int res = visibleTreeNode(root);
        Assertion.assertEquals(4, res);

        test1 = "3 1 3 x x 3 x x 1 2 5 x x x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = visibleTreeNode(root);
        Assertion.assertEquals(4, res);

        test1 = "5 8 3 x x 8 x x 6 x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = visibleTreeNode(root);
        Assertion.assertEquals(4, res);

        test1 = "9 8 11 x x 20 x x 6 x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = visibleTreeNode(root);
        Assertion.assertEquals(3, res);

        test1 = "-100 x -500 x -50 x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = visibleTreeNode(root);
        Assertion.assertEquals(2, res);

        test1 = "5 4 3 x x 8 x x 6 x x";
        root = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        res = visibleTreeNode(root);
        Assertion.assertEquals(3, res);
        System.out.println("All tests passed!");
    }

    private static int visibleTreeNode(BinaryTreeNode<Integer> root) {
        if (root == null) return 0;

        return helper(root, root.getValue());

    }

    private static int helper(BinaryTreeNode<Integer> node, int currentMax) {
        if (node == null) return 0;
        int total = 0;
        if (node.getValue() >= currentMax) {
            total++;
            currentMax = node.getValue();
        }

        total += helper(node.getLeft(), currentMax);
        total += helper(node.getRight(), currentMax);
        return total;
    }
}
