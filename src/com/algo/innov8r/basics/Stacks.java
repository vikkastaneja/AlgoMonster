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
            push(4);
        }};

        Assertion.assertStackEquals("Stack comparison", expected, actual);
    }


    private static Stack<Integer> execute(List<String> program) {
        int numOperations = Integer.parseInt(program.get(0));
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < numOperations; i++) {
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
