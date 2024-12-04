package gradeCalculator.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuleTest {

    @Test
    void testModuleCreation() {
        Module module = new Module("COM5003", 20);

        assertEquals("COM5003", module.getModuleName());
        assertEquals(20, module.getCredits());
    }

    @Test
    void testToString() {
        Module module = new Module("COM5003", 20);
        assertEquals("COM5003 (20 credits)", module.toString());
    }
}