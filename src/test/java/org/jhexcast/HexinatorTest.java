package org.jhexcast;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HexinatorTest {

    @Test
    void constructorTest() {
        HexConfig hfcg = HexConfig.getInstance(null);
        Hexinator hexinator = new Hexinator(hfcg);

    }

    Hexinator getHexinator() {
        HexConfig hfcg = HexConfig.getInstance(null);
        hfcg.initialize(null);
        return new Hexinator(hfcg);
    }

    @Test
    void getHexStringFromVectorTest() {
        Hexinator hexinator = this.getHexinator();
        int[] abc = new int[] {10, 11, 12};
        String expectABC = hexinator.getHexStringFromVector(abc);
        assertEquals("ABC", expectABC);
    }

    @Test
    void getVectorFromIndexTest() {
        Hexinator hexinator = this.getHexinator();

        int[] vec = null;

        //4 size of 2 dimensions
        vec = hexinator.getVectorFromIndex(0);
        assertArrayEquals(vec, new int[]{0, 0});

        vec = hexinator.getVectorFromIndex(13);
        assertArrayEquals(vec, new int[]{3, 1});

        //7 size of 3 dimensions
        HexConfig hfcg = HexConfig.getInstance(null);
        hfcg.setSize(7);
        hfcg.setDimension(3);
        hexinator = new Hexinator(hfcg);
        vec = hexinator.getVectorFromIndex(271);
        assertArrayEquals(vec, new int[]{5, 3, 5});
    }


    @Test
    void getNextHexStringTest() {
    }

    @Test
    void getSequenceTest() {
    }
}