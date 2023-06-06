package com.algo.innov8r.binarytree;

import com.algo.innov8r.basics.BinaryTreeNode;

public class BinaryTreeMaxDepth {
    public static void main(String[]args){
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(20,
                        new BinaryTreeNode<>(30, null, null),
                        new BinaryTreeNode<>(40, null, new BinaryTreeNode<>(50, null, null))
                ),
                new BinaryTreeNode(60, null, new BinaryTreeNode(70, new BinaryTreeNode(80, null, new BinaryTreeNode(90, null, null)), null)));

        System.out.println(maxDepth(root));

        System.out.println(maxDepth(null));

        System.out.println(maxDepth(new BinaryTreeNode<>(100, null, null)));
    }

    private static int maxDepth(BinaryTreeNode<Integer> root) {
        int depth = helper(root);
        return  depth == 0 ? depth : depth - 1;
    }

    private static int helper(BinaryTreeNode<Integer> node) {
        if (node == null) return 0;
        return Math.max(helper(node.getLeft()), helper(node.getRight())) + 1;
    }
}
