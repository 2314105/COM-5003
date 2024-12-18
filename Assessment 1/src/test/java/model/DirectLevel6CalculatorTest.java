package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DirectLevel6CalculatorTest {

    @Test
    public void testStudentDClassification() {
        // Student D: JOU6006, JOU6063, JOU6093
        List<Module> modules = List.of(
                new Module("JOU6006", 60, 58),
                new Module("JOU6063", 30, 58),
                new Module("JOU6093", 30, 68)
        );

        // Calculate the average
        double average = DirectLevel6Calculator.calculateL6Average(modules);
        assertEquals(60.50, average, "Average should be 60.50");

        // Calculate the profile classification
        String profileClassification = DirectLevel6Calculator.calculateProfileClassification(modules);
        assertEquals("2.2", profileClassification, "Profile classification should be 2.2");

        // Calculate the final classification
        String finalClassification = DirectLevel6Calculator.calculateFinalClassification(modules);
        assertEquals("2.1", finalClassification, "Final classification should be 2.1");

        // Format and assert the result
        String result = DirectLevel6Calculator.formatResults(average, finalClassification, profileClassification);
        String expectedResult =
                "Method C - Average 1 L6: 60.50 (2.1)\n" +
                        "Method D - Profile Mark Classification: 2.2\n" +
                        "Resulting Classification: 2.1";
        assertEquals(expectedResult, result, "Formatted result is incorrect");
    }

    @Test
    public void testStudentEClassification() {
        // Student E: SHN6033, SHN6053, SHN6073, SHN6143
        List<Module> modules = List.of(
                new Module("SHN6033", 30, 68),
                new Module("SHN6053", 30, 65),
                new Module("SHN6073", 30, 62),
                new Module("SHN6143", 30, 42)
        );

        // Calculate the average
        double average = DirectLevel6Calculator.calculateL6Average(modules);
        assertEquals(59.25, average, "Average should be 59.25");

        // Calculate the profile classification
        String profileClassification = DirectLevel6Calculator.calculateProfileClassification(modules);
        assertEquals("2.1", profileClassification, "Profile classification should be 2.1");

        // Calculate the final classification
        String finalClassification = DirectLevel6Calculator.calculateFinalClassification(modules);
        assertEquals("2.1", finalClassification, "Final classification should be 2.1");

        // Format and assert the result
        String result = DirectLevel6Calculator.formatResults(average, finalClassification, profileClassification);
        String expectedResult =
                "Method C - Average 1 L6: 59.25 (2.2)\n" +
                        "Method D - Profile Mark Classification: 2.1\n" +
                        "Resulting Classification: 2.1";
        assertEquals(expectedResult, result, "Formatted result is incorrect");
    }
}