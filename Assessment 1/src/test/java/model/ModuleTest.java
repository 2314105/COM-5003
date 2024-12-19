package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
This class tests the functionality of the Module class, including its constructor and getters.
It ensures the class behaves correctly for various scenarios, such as normal, edge cases, and invalid inputs.
*/
public class ModuleTest {

    /*
    Test the Module constructor and getter methods.
    Verifies that the constructor correctly initializes the module's attributes
    and the getter methods return the expected values.
    */
    @Test
    public void testModuleConstructorAndGetters() {
        // Arrange: Create a Module instance with valid data
        Module module = new Module("COM5015", 120, 85.5);

        // Assert: Check if the values are set correctly
        assertEquals("COM5015", module.getCode(), "Module code should be initialized correctly.");
        assertEquals(120, module.getCredits(), "Module credits should be initialized correctly.");
        assertEquals(85.5, module.getMarks(), 0.001, "Module marks should be initialized correctly.");
    }

    /*
    Test a Module instance with zero credits.
    Ensures that the constructor handles a scenario where a module has zero credits,
    which could represent a non-credit-bearing module.
    */
    @Test
    public void testModuleWithZeroCredits() {
        // Arrange: Create a module with zero credits
        Module module = new Module("COM5020", 0, 75.0);

        // Assert: Verify the module's attributes
        assertEquals(0, module.getCredits(), "Module credits should be zero.");
        assertEquals(75.0, module.getMarks(), 0.001, "Module marks should be initialized correctly.");
    }

    /*
    Test a Module instance with edge case marks.
    Verifies that the constructor can handle the lowest and highest possible marks (0 and 100).
    */
    @Test
    public void testModuleWithEdgeCaseMarks() {
        // Arrange & Act: Create a module with 0 marks
        Module module = new Module("COM5030", 60, 0.0);
        // Assert: Verify the marks are correctly set to 0
        assertEquals(0.0, module.getMarks(), 0.001, "Module marks should be zero.");

        // Arrange & Act: Create a module with 100 marks
        module = new Module("COM5040", 60, 100.0);
        // Assert: Verify the marks are correctly set to 100
        assertEquals(100.0, module.getMarks(), 0.001, "Module marks should be 100.");
    }
}
