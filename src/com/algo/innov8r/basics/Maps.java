package com.algo.innov8r.basics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.algo.innov8r.common.Assertion;

import java.util.List;
public class Maps {

    public static void main(String[] args) {
        Map<Integer, Integer> actual = getCounter(Arrays.asList(3, 4, 2, 1, 4, 4, 3));
        Map<Integer, Integer> expected = new HashMap<>() {{
            put(1, 1);
            put(2, 1);
            put(3, 2);
            put(4, 3);
        }};

        Assertion.assertMapEquals("Map test 1", expected, actual);

        actual = getCounter(Arrays.asList(8, 8, 17, 3, 0, 0, 22));
        expected = new HashMap<>() {{
            put(0, 2);
            put(3, 1);
            put(8, 2);
            put(17, 1);
            put(22, 1);
        }};

        Assertion.assertMapEquals("Map test 2", expected, actual);

        actual = getCounter(Arrays.asList());
        expected = new HashMap<>();

        Assertion.assertMapEquals("Map test 3", expected, actual);

        actual = getCounter(Arrays.asList(1, 1, 1));
        expected = new HashMap<>() {{
            put(1, 3);
        }};

        Assertion.assertMapEquals("Map test 4", expected, actual);
    }

    private static Map<Integer, Integer> getCounter(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int current : list) {
            if (map.containsKey(current)) {
                map.put(current, map.get(current) + 1);
            } else {
                map.put(current, 1);
            }
        }

        return map;
    }

}
