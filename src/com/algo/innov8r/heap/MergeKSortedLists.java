package com.algo.innov8r.heap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

import com.algo.innov8r.common.Assertion;

public class MergeKSortedLists {

    private static class Node {
        int current_index;
        List<Integer> list;
        public Node(List<Integer> list, int index) {
            this.current_index = index;
            this.list = list;
        }
    }
    
    public static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
        // One way is to keep a destination list and iterate through the input lists and keep merging the elements. In this way, we will be merging repeatedly the O(n) algorithm, which will increase the runtime linearly.
        // To optimize the algorithm, we will keep a heap that will keep the repetition algorithm execution to logarithmic runtime. 
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.list.get(n1.current_index) - n2.list.get(n2.current_index);
            }
        });

        for(List<Integer> list : lists) {
            pq.add(new Node(list, 0));
        }

        List<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()){
            Node node = pq.remove();
            list.add(node.list.get(node.current_index));
            node.current_index += 1;
            if (node.current_index < node.list.size()) {
                pq.add(node);
            }
        }

        return list;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        // Test 1
        String[] inputs = {"3","7 10","1 3 5","2 4 6"};
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        for (String s : inputs) {
            pw.println(s);
        }
        InputStream ip = new ByteArrayInputStream(sw.toString().getBytes());
        Scanner scanner = new Scanner(ip);
        int listsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < listsLength; i++) {
            lists.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<Integer> res = mergeKSortedLists(lists);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        List<Integer> expected = Arrays.asList(1,2,3,4,5,6,7,10);

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }

        // Test 2
        String[] inputs1 = {"1","1 2 3"};
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        for (String s : inputs1) {
            pw.println(s);
        }
        ip = new ByteArrayInputStream(sw.toString().getBytes());
        scanner = new Scanner(ip);
        listsLength = Integer.parseInt(scanner.nextLine());
        lists = new ArrayList<>();
        for (int i = 0; i < listsLength; i++) {
            lists.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        res = mergeKSortedLists(lists);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        expected = Arrays.asList(1,2,3);

        Assertion.assertEquals("Length of two lists", expected.size(), res.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertion.assertTrue("Index: " + String.valueOf(i), expected.get(i).equals(res.get(i)));
        }
    }
}
