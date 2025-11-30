package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;
import com.algo.innov8r.common.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class InsertIntoBST {
    public static BinaryTreeNode<Integer> insertBst(BinaryTreeNode<Integer> bst, int val) {
        if (bst == null) return new BinaryTreeNode<Integer>(val, null, null);
        if (val < bst.getValue()) {
            bst.setLeft(insertBst(bst.getLeft(), val));
        } else if (val > bst.getValue()) {
            bst.setRight(insertBst(bst.getRight(), val));
        }

        return bst;
    }

    private static void helper(BinaryTreeNode<Integer> bst, int val) {
        if (bst == null) return;
        if (val < bst.getValue()) {
            if (bst.getLeft() == null) {
                bst.setLeft(new BinaryTreeNode<Integer>(val, null, null));
            } else {
                insertBst(bst.getLeft(), val);
            }
        } else if (val > bst.getValue()){
            if (bst.getRight() == null) {
                bst.setRight(new BinaryTreeNode<Integer>(val, null, null));
            } else {
                insertBst(bst.getRight(), val);
            }
        }

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
        String value = "8 4 2 1 x x 3 x x 6 x x 12 10 x x 14 x 15 x x";
        BinaryTreeNode<Integer> bst = buildTree(splitWords(value).iterator(), Integer::parseInt);
        int val = 7;
        BinaryTreeNode<Integer> res = insertBst(bst, val);
        ArrayList<String> resArr = new ArrayList<>();
        formatTree(res, resArr);
        Assertion.assertEquals(String.join(" ", resArr), "8 4 2 1 x x 3 x x 6 x 7 x x 12 10 x x 14 x 15 x x");

        value = "37 19 2 x x 28 23 x x 35 x x 44 x 58 52 x x 67 x x";
        bst = buildTree(splitWords(value).iterator(), Integer::parseInt);
        val = 42;
        res = insertBst(bst, val);
        resArr = new ArrayList<>();
        formatTree(res, resArr);
        Assertion.assertEquals(String.join(" ", resArr), "37 19 2 x x 28 23 x x 35 x x 44 42 x x 58 52 x x 67 x x");

        value = "421 223 79 42 x x 157 133 x x x 327 x 404 356 x x 415 x x 741 626 x x 887 801 795 x x 842 x x 903 x 977 x x";
        bst = buildTree(splitWords(value).iterator(), Integer::parseInt);
        val = 404;
        res = insertBst(bst, val);
        resArr = new ArrayList<>();
        formatTree(res, resArr);
        Assertion.assertEquals(String.join(" ", resArr),
                "421 223 79 42 x x 157 133 x x x 327 x 404 356 x x 415 x x 741 626 x x 887 801 795 x x 842 x x 903 x 977 x x");

        value = "13 7 5 2 x x x 9 x x 15 14 x x 16 x 18 x x";
        bst = buildTree(splitWords(value).iterator(), Integer::parseInt);
        val = 12;
        res = insertBst(bst, val);
        resArr = new ArrayList<>();
        formatTree(res, resArr);
        Assertion.assertEquals(String.join(" ", resArr), "13 7 5 2 x x x 9 x 12 x x 15 14 x x 16 x 18 x x");

        value = "x";
        bst = buildTree(splitWords(value).iterator(), Integer::parseInt);
        val = 18;
        res = insertBst(bst, val);
        resArr = new ArrayList<>();
        formatTree(res, resArr);
        Assertion.assertEquals(String.join(" ", resArr), "18 x x");

        value = "37 19 2 x x 28 23 x x 35 x x 44 x 58 52 x x 67 x x";
        bst = buildTree(splitWords(value).iterator(), Integer::parseInt);
        val = 84;
        res = insertBst(bst, val);
        resArr = new ArrayList<>();
        formatTree(res, resArr);
        Assertion.assertEquals(String.join(" ", resArr), "37 19 2 x x 28 23 x x 35 x x 44 x 58 52 x x 67 x 84 x x");

    }
}
