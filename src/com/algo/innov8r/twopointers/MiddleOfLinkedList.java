package com.algo.innov8r.twopointers;

import com.algo.innov8r.basics.LinkedListNode;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class MiddleOfLinkedList {

    public static int middleOfLinkedList(LinkedListNode<Integer> head) {
        // x 1 2 3 4 5
        //       s
        //             f
        // x 1 2 3 4 5 6
        //         s
        //               f
        LinkedListNode<Integer> preHead = new LinkedListNode<>(Integer.MIN_VALUE, head);
        LinkedListNode<Integer> slow = preHead;
        LinkedListNode<Integer> fast = preHead;
        while (fast != null) {
            slow = slow.getNext();
            fast = fast.getNext();
            if (fast != null) {
                fast = fast.getNext();
            }
        }

        return slow.getValue();
    }

    public static <T> LinkedListNode<T> buildList(Iterator<String> iter, Function<String, T> f) {
        if (!iter.hasNext()) return null;
        String val = iter.next();
        LinkedListNode<T> next = buildList(iter, f);
        return new LinkedListNode<T>(f.apply(val), next);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        String val = "0 1 2 3 4";
        LinkedListNode<Integer> head = buildList(splitWords(val).iterator(), Integer::parseInt);
        int res = middleOfLinkedList(head);
        Assert.assertEquals(2, res);

        val = "0 1 2 3 4 5";
        head = buildList(splitWords(val).iterator(), Integer::parseInt);
        res = middleOfLinkedList(head);
        Assert.assertEquals(3, res);
    }
}
