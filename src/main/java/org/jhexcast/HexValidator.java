package org.jhexcast;

import org.jhexcast.filters.HexFilter;
import org.jhexcast.filters.HexSpeakFilter;
import org.jhexcast.filters.MinimumUniqueDigitsFilter;
import org.jhexcast.filters.SequenceDigitsFilter;

public class HexValidator {
    protected static int MAX_TRIES = 1000;
    protected HexFilter[] hexFilters = null;

    public HexValidator() {
        this.hexFilters = new HexFilter[] {
                new HexSpeakFilter(),
                new SequenceDigitsFilter(),
                new MinimumUniqueDigitsFilter()
        };
    }

    public boolean isValid(String candidate) {
        for (HexFilter hexFilter: this.hexFilters) {
            if (!hexFilter.isValid(candidate)) {
                return false;
            }
        }
        return true;
    }
}



//
//    def is_valid(self, candidate):
//        for f in self.filters:
//            if not f.is_valid(candidate):
//                return False
//        return True

//    def __init__(self) -> None:
//        self.filters = [
//            HexSpeakFilter(),
//            SequenceHexDigitsFilter(),
//            MinimumUniqueDigitsFilter()
//        ]
