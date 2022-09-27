package org.jhexcast;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JhexcastTest {

    @Test
    void getActionTest() {
    }

    @Test
    void handleOutputTest() {
        Jhexcast jhc = new Jhexcast();

        jhc.handleOutput(Jhexcast.UNKNOWN_ACTION, "gilg");
        jhc.handleOutput(Jhexcast.NO_INIT_ACTION,null);

    }

    @Test
    void performSequenceTest() {
        Jhexcast jhc = new Jhexcast();
        jhc.performSequence();
    }
}