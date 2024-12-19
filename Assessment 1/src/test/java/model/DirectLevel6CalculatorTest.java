package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
This class tests the functionality of the DirectLevel6Calculator.
It ensures that the methods for calculating degree classifications
produce accurate results for given module data.
*/
class DirectLevel6CalculatorTest {

    private DirectLevel6Calculator calculator;

    /*
    Sets up the test environment before each test case.
    Initializes a new DirectLevel6Calculator with dependencies.
    */
    @BeforeEach
    void setUp() {
        // Create instances of AverageCalculator and MarkClassifier to inject into the calculator
        AverageCalculator averageCalculator = new AverageCalculator();
        MarkClassifier markClassifier = new MarkClassifier();
        calculator = new DirectLevel6Calculator(averageCalculator, markClassifier);
    }

    /*
    Test case: Validates the final classification calculation for "Student D".
    Ensures that the calculated output matches the expected classification.
    */
    @Test
    void testStudentDClassification() {
        // Arrange: Define the modules for Student D
        List<Module> modules = List.of(
                new Module("JOU6006", 60, 58), // Module with 60 credits and marks 58
                new Module("JOU6063", 30, 58), // Module with 30 credits and marks 58
                new Module("JOU6093", 30, 68)  // Module with 30 credits and marks 68
        );

        // Expected output based on the classification logic
        String expectedOutput = """
                Method C - Average 1 L6: 60.50 (2.1)
                Method D - Profile Mark Classification: 2.2
                Resulting Classification: 2.1
                """;

        // Act: Perform the final classification calculation
        String actualOutput = calculator.calculateFinalClassification(modules);

        // Assert: Verify the actual output matches the expected output
        assertEquals(expectedOutput, actualOutput);
    }

    /*
    Test case: Validates the final classification calculation for "Student E".
    Ensures that the calculated output matches the expected classification.
    */
    @Test
    void testStudentEClassification() {
        // Arrange: Define the modules for Student E
        List<Module> modules = List.of(
                new Module("SHN6033", 30, 68), // Module with 30 credits and marks 68
                new Module("SHN6053", 30, 65), // Module with 30 credits and marks 65
                new Module("SHN6073", 30, 62), // Module with 30 credits and marks 62
                new Module("SHN6143", 30, 42)  // Module with 30 credits and marks 42
        );

        // Expected output based on the classification logic
        String expectedOutput = """
                Method C - Average 1 L6: 59.25 (2.2)
                Method D - Profile Mark Classification: 2.1
                Resulting Classification: 2.1
                """;

        // Act: Perform the final classification calculation
        String actualOutput = calculator.calculateFinalClassification(modules);

        // Assert: Verify the actual output matches the expected output
        assertEquals(expectedOutput, actualOutput);
    }
}
