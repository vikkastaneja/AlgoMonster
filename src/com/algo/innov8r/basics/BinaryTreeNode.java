package com.algo.innov8r.basics;

public class BinaryTreeNode<T> {
    private T value;

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    private BinaryTreeNode<T> left, right;
    public BinaryTreeNode(T value, BinaryTreeNode left, BinaryTreeNode right) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
