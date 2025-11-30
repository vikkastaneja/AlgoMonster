package com.algo.innov8r.breadthfirstsearch;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.algo.innov8r.common.Assertion;

// NOTE: Traversal is the same as level order traversal.
// The only difference is that when adding to the list, we will add first or add last based on the traversal direction
// Runtime: O(number of nodes in the tree) --> Linear
// Memory: O(max number of leaf nodes because at any point in time, a level will be in the queue) + O(number of nodes * size of Integer)
public class BinaryTreeZigZagLevelOrderTraversal {
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

    public static List<List<Integer>> zigZagTraversal(Node<Integer> root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        boolean flip = false;
        while(!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node<Integer> current = queue.remove();
                if (flip) {
                    temp.addFirst(current.val);
                } else {
                    temp.add(current.val);
                }

                if (current.left != null) {
                    queue.add(current.left);
                }

                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            flip = !flip;
            list.add(temp);
        }

        return list;
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
        Node<Integer> root = buildTree(splitWords("1 2 4 8 x x 9 x x 5 10 x x x 3 6 x 11 x x 7 12 x x 13 x x").iterator(), Integer::parseInt);
        List<List<Integer>> res = zigZagTraversal(root);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

    
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1), Arrays.asList(3, 2), Arrays.asList(4,5,6, 7), Arrays.asList(13,12,11,10,9,8));

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }


        root = buildTree(splitWords("1 2 4 x 7 x x 5 x 8 x x 3 x 6 x x").iterator(), Integer::parseInt);
        
        res = zigZagTraversal(root);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        expected = Arrays.asList(Arrays.asList(1), Arrays.asList(3, 2), Arrays.asList(4,5,6), Arrays.asList(8,7));

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }
    }
}
