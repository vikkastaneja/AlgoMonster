package com.algo.innov8r.basics;
import java.util.Stack;

import com.algo.innov8r.common.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stacks {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(
            "8", "push 3", "push 7", "push 20", "peek", "pop",
                  "push 0", "push 4", "pop");

        Stack<Integer> actual = execute(list);
        Stack<Integer> expected = new Stack<>(){{
            push(3);
            push(7);
            push(0);
        }};

        Assertion.assertStackEquals("Stack comparison 1", expected, actual);

        list = Arrays.asList("8", "push 3", "push 7", "push 8",
                            "peek", "pop", "peek", "pop", "pop");
        actual = execute(list);
        expected = new Stack<Integer>(){{
        }};

        Assertion.assertStackEquals("Stack comparison 2", expected, actual);

        list = Arrays.asList("6", "push 5", "push 10", "peek",
                        "pop", "push 15", "peek");
        actual = execute(list);
        expected = new Stack<>() {{
            add(5);
            add(15);
        }};

        Assertion.assertStackEquals("Stack comparison 3", expected, actual);

        list = Arrays.asList("5", "push 1", "push 2", "push 3",
                        "pop", "pop");
        actual = execute(list);
        expected = new Stack<>() {{
            add(1);
        }};

        Assertion.assertStackEquals("Stack comparison 4", expected, actual);

        list = Arrays.asList("7", "push 100", "peek", "push 200", "peek", "push 300",
                        "peek", "pop");
        actual = execute(list);
        expected = new Stack<>() {{
            add(100);
            add(200);
        }};

        Assertion.assertStackEquals("Stack comparison 5", expected, actual);

        list = Arrays.asList("4", "push 42", "push 24", "pop", "peek");
        actual = execute(list);
        expected = new Stack<>() {{
            add(42);
        }};

        Assertion.assertStackEquals("Stack comparison 6", expected, actual);

        list = Arrays.asList("8", "push 1", "push 2", "push 3", "push 4", "peek",
                        "pop", "peek", "pop");
        actual = execute(list);
        expected = new Stack<>() {{
            add(1);
            add(2);
        }};

        Assertion.assertStackEquals("Stack comparison 7", expected, actual);

        list = Arrays.asList("6", "push 9", "push 8", "push 7", "peek",
                        "pop", "peek");
        actual = execute(list);
        expected = new Stack<>() {{
            add(9);
            add(8);
        }};

        Assertion.assertStackEquals("Stack comparison 8", expected, actual);
    }


    private static Stack<Integer> execute(List<String> program) {
        int numOperations = Integer.parseInt(program.get(0));
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= numOperations; i++) {
            String[] s = program.get(i).split(" ");
            if (s[0].trim().equals("push")) {
                int val = Integer.parseInt(s[1].trim());
                stack.push(val);
            } else if (s[0].trim().equals("pop")) {
                System.out.println("pop: " + stack.pop());
            } else if (s[0].trim().equals("peek")) {
                System.out.println("Peek: " + stack.peek());
            }
        }

        return stack;
    }
}
