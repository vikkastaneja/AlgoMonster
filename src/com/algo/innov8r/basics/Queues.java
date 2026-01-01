package com.algo.innov8r.basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.algo.innov8r.common.Assertion;

public class Queues {
    public static void main(String[] args) {
        Queue<Integer> actual = new LinkedList<>() {{
            add(7);
            add(6);
            add(4);
            add(2);
            add(8);
            add(0);
        }};

        rotateLeftTillZero(actual);
        Queue<Integer> expected = new LinkedList<>() {{
            add(0);
            add(7);
            add(6);
            add(4);
            add(2);
            add(8);
        }};

        Assertion.assertQueueEquals("Queue test 1", expected, actual);


        actual = new LinkedList<>() {{
            add(0);
            add(3);
            add(4);
            add(6);
            add(3);
        }};

        rotateLeftTillZero(actual);
        expected = new LinkedList<>() {{
            add(0);
            add(3);
            add(4);
            add(6);
            add(3);
        }};

        Assertion.assertQueueEquals("Queue test 2", expected, actual);

        actual = new LinkedList<>() {{
            add(5);
            add(0);
            add(3);
            add(2);
            add(1);
        }};

        rotateLeftTillZero(actual);
        expected = new LinkedList<>() {{
            add(0);
            add(3);
            add(2);
            add(1);
            add(5);
        }};

        Assertion.assertQueueEquals("Queue test 3", expected, actual);

        actual = new LinkedList<>() {{
            add(0);
        }};

        rotateLeftTillZero(actual);
        expected = new LinkedList<>() {{
            add(0);
        }};

        Assertion.assertQueueEquals("Queue test 4", expected, actual);

        actual = new LinkedList<>() {{
            add(10);
            add(20);
            add(30);
            add(0);
            add(40);
            add(50);
        }};

        rotateLeftTillZero(actual);
        expected = new LinkedList<>() {{
            add(0);
            add(40);
            add(50);
            add(10);
            add(20);
            add(30);
        }};

        Assertion.assertQueueEquals("Queue test 5", expected, actual);
        
        actual = new LinkedList<>() {{
            add(0);
            add(0);
            add(0);
        }};

        rotateLeftTillZero(actual);
        expected = new LinkedList<>() {{
            add(0);
            add(0);
            add(0);
        }};

        Assertion.assertQueueEquals("Queue test 6", expected, actual);

        actual = new LinkedList<>();

        rotateLeftTillZero(actual);
        expected = new LinkedList<>();

        Assertion.assertQueueEquals("Queue test 7", expected, actual);

        actual = new LinkedList<>() {{
            add(1);
            add(2);
            add(3);
        }};

        rotateLeftTillZero(actual);
        expected = new LinkedList<>() {{
            add(1);
            add(2);
            add(3);
        }};

        Assertion.assertQueueEquals("Queue test 8", expected, actual);

    }

    static void rotateLeftTillZero(Queue<Integer> queue) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            queue.add(queue.remove());
            if (queue.peek() == 0) break;
        }
    }
}
