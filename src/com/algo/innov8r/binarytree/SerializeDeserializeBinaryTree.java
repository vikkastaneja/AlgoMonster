package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;

public class SerializeDeserializeBinaryTree {
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
        String serialized = serialize(root);
        System.out.println(serialized);

        String[]split = serialized.split(" ");
        BinaryTreeNode<Integer> des = deserialize(Arrays.asList(split).iterator());
        System.out.println(serialize(des));
    }

    private static BinaryTreeNode<Integer> deserialize(Iterator<String> iterator) {
        String val = iterator.next();
        if (val.equals("x")) return null;
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(Integer.parseInt(val), null, null);
        node.setLeft(deserialize(iterator));
        node.setRight(deserialize(iterator));
        return node;
    }

    private static String serialize(BinaryTreeNode<Integer> root) {
        if (root == null) return "x";
        StringJoiner sb = new StringJoiner(" ");
        sb.add(String.valueOf(root.getValue()));
        sb.add(serialize(root.getLeft()));
        sb.add(serialize(root.getRight()));
        return sb.toString();
    }
}
