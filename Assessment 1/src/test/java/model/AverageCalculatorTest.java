package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageCalculatorTest {

    @Test
    void testCalculateAverageWithValidModules() {
        // Arrange
        List<Module> modules = List.of(
                new Module("CS101", 30, 70.0),
                new Module("CS102", 60, 80.0),
                new Module("CS103", 30, 60.0)
        );

        // Act
        double average = AverageCalculator.calculateAverage(modules);

        // Assert
        double expectedAverage = (30 * 70 + 60 * 80 + 30 * 60) / 120.0; // Weighted average
        assertEquals(expectedAverage, average, 0.01); // Allow small delta for floating-point precision
    }

    @Test
    void testCalculateAverageWithNoModules() {
        // Arrange
        List<Module> modules = List.of();

        // Act
        double average = AverageCalculator.calculateAverage(modules);

        // Assert
        assertEquals(0.0, average);
    }

    @Test
    void testCalculateAverageWithZeroCreditsModule() {
        // Arrange
        List<Module> modules = List.of(
                new Module("CS101", 30, 70.0),
                new Module("CS102", 0, 50.0), // Zero credits, should be ignored
                new Module("CS103", 30, 60.0)
        );

        // Act
        double average = AverageCalculator.calculateAverage(modules);

        // Assert
        double expectedAverage = (30 * 70 + 30 * 60) / 60.0; // Weighted average excluding zero-credit module
        assertEquals(expectedAverage, average, 0.01);
    }

    @Test
    void testCalculateAverageWithSingleModule() {
        // Arrange
        List<Module> modules = List.of(new Module("CS101", 15, 85.0));

        // Act
        double average = AverageCalculator.calculateAverage(modules);

        // Assert
        assertEquals(85.0, average, 0.01); // Average equals the single module's mark
    }

    @Test
    void testCalculateAverageWithMixedModules() {
        // Arrange
        List<Module> modules = List.of(
                new Module("CS101", 20, 90.0),
                new Module("CS102", 10, 75.0),
                new Module("CS103", 50, 65.0)
        );

        // Act
        double average = AverageCalculator.calculateAverage(modules);

        // Assert
        double expectedAverage = (20 * 90 + 10 * 75 + 50 * 65) / 80.0; // Weighted average
        assertEquals(expectedAverage, average, 0.01);
    }
}
