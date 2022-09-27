package org.jhexcast.filters;

import org.jhexcast.utils.Hexutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequenceDigitsFilter implements HexFilter {

    int minimum = 3;

    public SequenceDigitsFilter(Integer mininum) {
        if(mininum != null) {
            this.minimum = mininum;
        }
    }

    public boolean isHexSequence(String candidate) {

        char[] candchars = candidate.toCharArray();

        int[] hexvals = Hexutils.toHexArray(candchars, null);
        int[] hexvalPlus = Hexutils.toHexArray(candchars, (v) -> {return (v + 1);});

        int[] allButFirst = Arrays.copyOfRange(hexvals, 1, hexvals.length);
        int[] allButLast = Arrays.copyOfRange(hexvalPlus, 0, hexvalPlus.length-1);

        return (Arrays.compare(allButFirst, allButLast) == 0);
    }


    /**
     * No set of 3 consecutive digits can repeat
     * No set of any 2 digits can repeat more than twice
     * No more than 3 chars in any sequence ever
     */
    @Override
    public boolean isValid(String candidate) {
        String[] candidateArr = candidate.split("");
        List<String> windows = Hexutils.getWindows(candidateArr, 3);

        // no consecutive 3 sequences
        for (String window : windows) {
            if (candidate.contains(String.format("%s%s", window, window))) {
                return false;
            }
        }

        // no 3 consecutive 2-sequences
        windows = Hexutils.getWindows(candidateArr, 2);
        for (String window : windows) {
            if (candidate.contains(String.format("%s%s%s", window, window, window))) {
                return false;
            }
        }

        // no 4-8 consecutive sequences
        for (int i = 4; i <= 8 ; i++) {
            windows = Hexutils.getWindows(candidateArr, i);
            for (String window : windows) {
                if (this.isHexSequence(window)) {
                    return false;
                }
            }
        }
        return true;
    }
}

