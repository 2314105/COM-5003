package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
This class contains unit tests for the AverageCalculator class.
It validates the calculateAverage method with various test cases.
*/
class AverageCalculatorTest {

    /*
    Test case: Validates the calculation of the weighted average
    when the input list contains multiple valid modules with credits and marks.
    */
    @Test
    void testCalculateAverageWithValidModules() {
        // Arrange: Create a list of modules with valid credits and marks
        List<Module> modules = List.of(
                new Module("CS101", 30, 70.0),
                new Module("CS102", 60, 80.0),
                new Module("CS103", 30, 60.0)
        );

        // Act: Calculate the average
        double average = AverageCalculator.calculateAverage(modules);

        // Assert: Verify the calculated average matches the expected value
        double expectedAverage = (30 * 70 + 60 * 80 + 30 * 60) / 120.0; // Weighted average
        assertEquals(expectedAverage, average, 0.01); // Allow small delta for floating-point precision
    }

    /*
    Test case: Validates the behavior of calculateAverage when the input list is empty.
    The method should return 0 in this case.
    */
    @Test
    void testCalculateAverageWithNoModules() {
        // Arrange: Create an empty list of modules
        List<Module> modules = List.of();

        // Act: Calculate the average
        double average = AverageCalculator.calculateAverage(modules);

        // Assert: Verify the average is 0.0
        assertEquals(0.0, average);
    }

    /*
    Test case: Validates the calculation of the weighted average
    when one of the modules has zero credits. Such modules should be ignored in the calculation.
    */
    @Test
    void testCalculateAverageWithZeroCreditsModule() {
        // Arrange: Create a list of modules where one has zero credits
        List<Module> modules = List.of(
                new Module("CS101", 30, 70.0),
                new Module("CS102", 0, 50.0), // Zero credits, should be ignored
                new Module("CS103", 30, 60.0)
        );

        // Act: Calculate the average
        double average = AverageCalculator.calculateAverage(modules);

        // Assert: Verify the average excludes the zero-credit module
        double expectedAverage = (30 * 70 + 30 * 60) / 60.0; // Weighted average excluding zero-credit module
        assertEquals(expectedAverage, average, 0.01);
    }

    /*
    Test case: Validates the calculation of the weighted average
    when the input list contains only a single module.
    The average should equal the marks of the single module.
    */
    @Test
    void testCalculateAverageWithSingleModule() {
        // Arrange: Create a list with a single module
        List<Module> modules = List.of(new Module("CS101", 15, 85.0));

        // Act: Calculate the average
        double average = AverageCalculator.calculateAverage(modules);

        // Assert: Verify the average matches the single module's mark
        assertEquals(85.0, average, 0.01); // Average equals the single module's mark
    }

    /*
    Test case: Validates the calculation of the weighted average
    when the input list contains modules with varying credits and marks.
    */
    @Test
    void testCalculateAverageWithMixedModules() {
        // Arrange: Create a list of modules with diverse credits and marks
        List<Module> modules = List.of(
                new Module("CS101", 20, 90.0),
                new Module("CS102", 10, 75.0),
                new Module("CS103", 50, 65.0)
        );

        // Act: Calculate the average
        double average = AverageCalculator.calculateAverage(modules);

        // Assert: Verify the calculated average matches the expected value
        double expectedAverage = (20 * 90 + 10 * 75 + 50 * 65) / 80.0; // Weighted average
        assertEquals(expectedAverage, average, 0.01);
    }
}
