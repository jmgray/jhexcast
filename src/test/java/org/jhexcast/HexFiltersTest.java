package org.jhexcast;

import static org.junit.jupiter.api.Assertions.*;

import org.jhexcast.filters.HexFilter;
import org.jhexcast.filters.HexSpeakFilter;
import org.jhexcast.filters.MinimumUniqueDigitsFilter;
import org.jhexcast.filters.SequenceDigitsFilter;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;


public class HexFiltersTest {

    protected void handleGoodBads(HexFilter filter, String[] goodItems, String[] badItems) {

        for (String candg : goodItems) {
            assertTrue(filter.isValid(candg));
        }

        for (String candb : badItems) {
            assertFalse(filter.isValid(candb));
        }
    }

    @Test
    void MinimumUniqueDigitsFilterTest() {
        MinimumUniqueDigitsFilter filter = new MinimumUniqueDigitsFilter(null);
        String[] goodItems = new String[]{"12341234", "ABABCCCDB"};
        String[] badItems = new String[]{"11112222", "122333221"};

        this.handleGoodBads(filter, goodItems, badItems);
    }

    @Test
    void SequenceDigitsFilterTest() {
        HexFilter filter = new SequenceDigitsFilter(null);
        String[] badItems  =new String[]{"12345", "abcde", "567"};
        String[] goodItems  =new String[]{"123345", "abfcde", "567a"};

        this.handleGoodBads(filter, goodItems, badItems);
    }
    
    @Test
    void SequenceDigitsFilterIsHexSequenceTest() {
        SequenceDigitsFilter filter = new SequenceDigitsFilter(null);
        
        String[] seqItems = new String[]{"ABC", "1234567", "789A", "DEF"};
        String[] noSeqItems = new String[]{"123F2367", "1212F345", "12ABC789"};

        for (String seq: seqItems) {
            assertTrue(filter.isHexSequence(seq));
        }
        for (String seq: noSeqItems) {
            assertFalse(filter.isHexSequence(seq));
        }
    }

    @Test
    void HexSpeakFilter() {
        HexSpeakFilter filter = new HexSpeakFilter();

        assertFalse(filter.isValid("ABCDBEEF"));
        assertTrue(filter.isValid("ABC13CAB"));
    }
}
