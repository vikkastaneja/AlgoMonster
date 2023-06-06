package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class LcaOnBST {
    public static int lcaOnBst(BinaryTreeNode<Integer> bst, int p, int q) {
        // We are assuming p < q
        // So to handle that, if p > q, add a recursive step
        if (p > q)return lcaOnBst(bst, q, p);
        if (p == bst.getValue()) return p;
        if (q == bst.getValue()) return q;
        if (p < bst.getValue() && q > bst.getValue()) return bst.getValue();

        if (p < bst.getValue()) return lcaOnBst(bst.getLeft(), p, q);
        else if (p > bst.getValue()) return lcaOnBst(bst.getRight(), p, q);
        return bst.getValue();
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
        String val = "6 2 0 x x 4 3 x x 5 x x 8 7 x x 9 x x";
        BinaryTreeNode<Integer> bst = buildTree(splitWords(val).iterator(), Integer::parseInt);
        int p = 2;
        int q = 8;
        int res = lcaOnBst(bst, p, q);
        Assert.assertEquals(6, res);

        val = "6 2 0 x x 4 3 x x 5 x x 8 7 x x 9 x x";
        bst = buildTree(splitWords(val).iterator(), Integer::parseInt);
        p = 2;
        q = 4;
        res = lcaOnBst(bst, p, q);
        Assert.assertEquals(2, res);

        val = "2 1 x x x";
        bst = buildTree(splitWords(val).iterator(), Integer::parseInt);
        p = 2;
        q = 1;
        res = lcaOnBst(bst, p, q);
        Assert.assertEquals(2, res);
    }
}
