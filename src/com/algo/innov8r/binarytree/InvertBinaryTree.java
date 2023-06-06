package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;

import java.util.*;
import java.util.function.Function;

public class InvertBinaryTree {
    public static BinaryTreeNode<Integer> invertBinaryTree(BinaryTreeNode<Integer> tree) {
        if (tree == null) return null;
        if (tree.getLeft() == null && tree.getRight() == null) return null;
        BinaryTreeNode<Integer> temp = tree.getLeft();
        tree.setLeft(tree.getRight());
        tree.setRight(temp);
        if (tree.getLeft() != null) {
            invertBinaryTree(tree.getLeft());
        }

        if (tree.getRight() != null) {
            invertBinaryTree(tree.getRight());
        }

        return tree;
        //1 3 6 x x x 2 5 x x 4 7 x x x
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

    public static <T> void formatTree(BinaryTreeNode<T> node, List<String> out) {
        if (node == null) {
            out.add("x");
            return;
        }
        out.add(node.getValue().toString());
        formatTree(node.getLeft(), out);
        formatTree(node.getRight(), out);
    }

    public static void main(String[] args) {
        String test1 = "1 2 4 x 7 x x 5 x x 3 x 6 x x";
        BinaryTreeNode<Integer> tree = buildTree(splitWords(test1).iterator(), Integer::parseInt);
        BinaryTreeNode<Integer> res = invertBinaryTree(tree);
        ArrayList<String> resArr = new ArrayList<>();
        formatTree(res, resArr);
        System.out.println(String.join(" ", resArr));
    }
}
