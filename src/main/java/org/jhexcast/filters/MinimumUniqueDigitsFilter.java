package org.jhexcast.filters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *  Must be at least 4 (by default) distinct chars
 */
public class MinimumUniqueDigitsFilter implements HexFilter {

    public static int defaultMinimum= 4;
    protected int minimum;


    public MinimumUniqueDigitsFilter(Integer minimum) {
        this.minimum = Objects.requireNonNullElseGet(minimum, () -> defaultMinimum);
    }


    @Override
    public boolean isValid(String candidate) {
        char[] candelms = candidate.toCharArray();
        Character[] chandelms = new Character[candelms.length];
        for (int i = 0; i < candelms.length; i++) {
            chandelms[i] = candelms[i];
        }
        Set<Character> chset = new HashSet<>(Arrays.asList(chandelms));
        return chset.size() >= this.minimum;
    }
}
