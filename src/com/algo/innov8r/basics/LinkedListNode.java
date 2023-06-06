package com.algo.innov8r.basics;

public class LinkedListNode<T> {
    private T value;
    private LinkedListNode<T> next;
    public LinkedListNode(T value, LinkedListNode<T> next) {
        this.next = next;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }
}
