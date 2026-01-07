package com.algo.innov8r.sorting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.algo.innov8r.common.Assertion;

/*
    Keep the left to current pointer sorted by keeping the current element to it's correct location.
    Complexity: O(n^2)
 */
public class CustomSorter {

    public class Task {
        public int id;
        public String description;
        public Task(int id, String desc) {
            this.id = id;
            this.description = desc;
        }
   
    }

    private void testCustomSorting() {
        Task minus2 = new Task(-2, "MinusTwo");
        Task minus1 = new Task(-1, "MinusOne");
        Task zero = new Task(0, "Zero");
        Task one = new Task(1, "One");
        Task two = new Task(2, "Two");
        Task three = new Task(3, "Three");
        
        List<Task> tasks = new ArrayList<>() {{
            add(two);
            add(one);
            add(minus1); 
            add(three);
            add(minus2);
            add(zero);
        }};

        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.id - t2.id;
            } 
        });

        for (Task t : tasks)
            System.out.println("Id: " + t.id + ", Description: " + t.description);
        List<Task> expected = new ArrayList<>() {{
            add(minus2);
            add(minus1);
            add(zero);
            add(one);
            add(two);
            add(three);
        }};

        Assertion.assertListEquals(expected, tasks);

        System.out.println("Checking reverse...");
        tasks = new ArrayList<Task>() {{
            add(one);
            add(minus1); 
            add(three);
            add(minus2);
            add(zero);
        }};

        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t2.id - t1.id;
            }
        });

        expected = new ArrayList<>() {{
            add(three);
            add(one);
            add(zero);
            add(minus1);
            add(minus2);
        }};
        
        for (Task t : tasks)
            System.out.println("Id: " + t.id + ", Description: " + t.description);

        Assertion.assertListEquals(expected, tasks);
    }
    public static void main(String[] args) {
        CustomSorter cs = new CustomSorter();
        cs.testCustomSorting();
    }

    
}

