package com.algo.innov8r.breadthfirstsearch;

import java.util.*;
import java.util.function.Function;

import com.algo.innov8r.common.Assertion;

public class BinaryTreeMinDepth {
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

    public static int binaryTreeMinDepth(Node<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return 0;
        }

        Queue<Node<Integer>> q = new LinkedList<>();
        int depth = 0;
        q.add(root);
        while (!q.isEmpty()) {
            int len = q.size();
            ans.add(q.peek().val);
            depth++;
            if (q.peek().left == null && q.peek().right == null) {
                depth--;
                break;
            }
            for (int i = 0; i < len; i++) {
                Node<Integer> curr = q.remove();
                if (curr.right != null) q.add(curr.right);
                if (curr.left != null) q.add(curr.left);
            }
        }

        return depth;
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
        int res = binaryTreeMinDepth(root);

        Assertion.assertEquals("Unexpected depth of Shallow Node", 2, res);

        root = buildTree(splitWords("0 x x").iterator(), Integer::parseInt);
        res = binaryTreeMinDepth(root);

        Assertion.assertEquals("Unexpected depth of Shallow Node", 0, res);
    }
}
