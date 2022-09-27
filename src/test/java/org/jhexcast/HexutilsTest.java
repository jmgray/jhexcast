package org.jhexcast;

import org.jhexcast.utils.Hexutils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HexutilsTest {

    @Test
    void dumpMapTest() {
        LinkedHashMap<String, Integer> amap = new LinkedHashMap<>();
        amap.put("one", 1);
        amap.put("two", 2);
        amap.put("three", 3);

        Hexutils.dumpMap(amap);
    }

    @Test
    void toHexArrayTest() {
        String somehexvals = "89ABC";

        char[] somehexchars = somehexvals.toCharArray();

        int[] hexvals = Hexutils.toHexArray(somehexchars, null);
        assertEquals(Arrays.compare(hexvals, new int[]{8, 9, 10, 11, 12}), 0);

        int[] hexvalsplus = Hexutils.toHexArray(somehexchars, (v) -> {return (v + 1);});
        assertEquals(Arrays.compare(hexvalsplus, new int[]{9, 10, 11, 12, 13}), 0);
    }

    @Test
    void getWindowsTest() {
        String[] testVals = null;
        List<String> res = null;

        testVals = new String[]{"1", "2", "3", "4"};
        res = Hexutils.getWindows(testVals, 3);
        assertEquals(2, res.size());
        assertEquals(res.get(0), "123");
        assertEquals(res.get(1), "234");

        testVals = new String[]{"1", "2", "3", "4"};
        res = Hexutils.getWindows(testVals, 5);
        assertEquals(1, res.size());
        assertEquals("1234", res.get(0));

        testVals = new String[]{"11", "32", "83", "41"};
        res = Hexutils.getWindows(testVals, 2);
        assertEquals(3, res.size());
        assertEquals("1132", res.get(0));
        assertEquals("3283", res.get(1));
        assertEquals("8341", res.get(2));
    }
}