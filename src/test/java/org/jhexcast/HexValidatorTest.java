package org.jhexcast;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexValidatorTest {

    @Test
    void isValid() {
        HexValidator validator = new HexValidator();
        String hexcan = "DEADBEEF";
        validator.isValid(hexcan);

    }
}