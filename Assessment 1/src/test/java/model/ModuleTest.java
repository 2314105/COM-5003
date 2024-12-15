package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModuleTest {

    // Test for constructor and getters
    @Test
    public void testModuleConstructorAndGetters() {
        // Create a Module instance
        Module module = new Module("Computer Science", 120, 85.5);

        // Assert the name, credits, and marks are initialized correctly
        assertEquals("Computer Science", module.getName());  // Replace with a getter for name if needed
        assertEquals(120, module.getCredits());
        assertEquals(85.5, module.getMarks(), 0.001);  // Allow for a small margin of error with doubles
    }

    // Test for getter when credits are zero
    @Test
    public void testModuleWithZeroCredits() {
        // Create a module with zero credits
        Module module = new Module("Mathematics", 0, 75.0);

        // Assert the credits and marks
        assertEquals(0, module.getCredits());
        assertEquals(75.0, module.getMarks(), 0.001);
    }

    // Test for a module with edge case marks (e.g., 0 and 100)
    @Test
    public void testModuleWithEdgeCaseMarks() {
        // Create a module with 0 marks
        Module module = new Module("History", 60, 0.0);
        assertEquals(0.0, module.getMarks(), 0.001);

        // Create a module with 100 marks
        module = new Module("Physics", 60, 100.0);
        assertEquals(100.0, module.getMarks(), 0.001);
    }
}