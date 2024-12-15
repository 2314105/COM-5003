package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DirectLevel6CalculatorTest {

    // Test for calculateL6Average
    @Test
    public void testCalculateL6Average() {
        Module module1 = new Module("Module 1", 20, 75);
        Module module2 = new Module("Module 2", 30, 65);
        Module module3 = new Module("Module 3", 10, 50);
        List<Module> modules = Arrays.asList(module1, module2, module3);

        double result = DirectLevel6Calculator.calculateL6Average(modules);

        // Calculate expected weighted average manually
        double expected = (20 * 75 + 30 * 65 + 10 * 50) / (20 + 30 + 10);

        // Assert that the result matches the expected value rounded to 2 decimal places
        assertEquals(expected, result, 0.01);
    }

    // Test for calculateProfileClassification
    @Test
    public void testCalculateProfileClassification() {
        Module module1 = new Module("Module 1", 20, 75); // First Class
        Module module2 = new Module("Module 2", 30, 65); // Upper Second Class
        Module module3 = new Module("Module 3", 10, 50); // Lower Second Class
        Module module4 = new Module("Module 4", 40, 80); // First Class
        List<Module> modules = Arrays.asList(module1, module2, module3, module4);

        String result = DirectLevel6Calculator.calculateProfileClassification(modules);

        // Assert the classification
        assertEquals("1st", result);
    }

    // Test for calculateFinalClassification
    @Test
    public void testCalculateFinalClassification() {
        Module module1 = new Module("Module 1", 20, 75); // First Class
        Module module2 = new Module("Module 2", 30, 65); // Upper Second Class
        Module module3 = new Module("Module 3", 10, 50); // Lower Second Class
        Module module4 = new Module("Module 4", 40, 80); // First Class
        List<Module> modules = Arrays.asList(module1, module2, module3, module4);

        String result = DirectLevel6Calculator.calculateFinalClassification(modules);

        // Expected output format
        String expected = "Average: 68.50\nClassification: 1st\nProfile Classification: 1st";

        // Assert the final classification result
        assertEquals(expected, result);
    }
}