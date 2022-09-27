package org.jhexcast;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexConfigTest {

    @Test
    void initializeSaveLoadTest() {
        HexConfig hcfg = HexConfig.getInstance(null);

        hcfg.setLeap(123);
        hcfg.saveProperties();

        hcfg = HexConfig.getLoadedInstance();
        assertEquals(hcfg.getLeap(), 123);
    }
}