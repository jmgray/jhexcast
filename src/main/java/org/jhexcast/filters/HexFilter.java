package org.jhexcast.filters;



public interface HexFilter {
//    def is_valid(self, candidate) -> bool:
    abstract
    public boolean isValid(String candidate);
}
