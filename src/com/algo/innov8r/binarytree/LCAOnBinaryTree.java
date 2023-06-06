package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class LCAOnBinaryTree {
    // Given two nodes of a binary tree, find their lowest common ancestor.
    // Logic is that if the two nodes are not in the same subtree, then the current node is the LCA
    // Else move to left subtree
    // If not found in left subtree, move to right subtree
    public static BinaryTreeNode<Integer> lcaOnBt(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> n1, BinaryTreeNode<Integer> n2) {
        if (node == null || node == n1 || node == n2) return node;
        BinaryTreeNode<Integer> left = lcaOnBt(node.getLeft(), n1, n2);
        BinaryTreeNode<Integer> right = lcaOnBt(node.getRight(), n1, n2);
        if (left != null && right != null) return node;
        else if (left != null) return left;
        else return right;
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

    public static BinaryTreeNode<Integer> findNode(BinaryTreeNode<Integer> root, int target) {
        if (root == null) return null;
        if (root.getValue() == target) return root;
        BinaryTreeNode<Integer> leftSearch = findNode(root.getLeft(), target);
        if (leftSearch != null) {
            return leftSearch;
        }
        return findNode(root.getRight(), target);
    }

    public static void main(String[] args) {
        String val = "6 4 3 x x 5 x x 8 x x";
        BinaryTreeNode<Integer> bt = buildTree(splitWords(val).iterator(), Integer::parseInt);
        BinaryTreeNode<Integer> p = findNode(bt, 4);
        BinaryTreeNode<Integer> q = findNode(bt, 8);
        BinaryTreeNode<Integer> res = lcaOnBt(bt, p, q);
        Assert.assertEquals(findNode(bt, 6), res);

        val = "6 4 3 x x 5 x x 8 x x";
        bt = buildTree(splitWords(val).iterator(), Integer::parseInt);
        p = findNode(bt, 4);
        q = findNode(bt, 6);
        res = lcaOnBt(bt, p, q);
        Assert.assertEquals(findNode(bt, 6), res);

        val = "x";
        bt = buildTree(splitWords(val).iterator(), Integer::parseInt);
        p = findNode(bt, 3);
        q = findNode(bt, 2);
        res = lcaOnBt(bt, p, q);
        Assert.assertEquals(null, res);

        val = "6 4 3 x x 5 x x 8 x x";
        bt = buildTree(splitWords(val).iterator(), Integer::parseInt);
        p = findNode(bt, 3);
        q = findNode(bt, 5);
        res = lcaOnBt(bt, p, q);
        Assert.assertEquals(findNode(bt, 4), res);
    }
}
