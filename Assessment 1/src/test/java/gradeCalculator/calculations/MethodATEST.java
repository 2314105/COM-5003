package gradeCalculator.calculations;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodATEST {

    @Test
    public void testCalculateWeightedAverage() {
        List<Integer> credits = Arrays.asList(10, 20, 20, 20, 10, 20, 20);
        List<Integer> marks = Arrays.asList(70, 70, 75, 50, 76, 58, 55);

        double result = MethodA.calculateWeightedAverage(credits, marks);
        assertEquals(63.50, result, 0.01, "Weighted average calculation failed");
    }

    @Test
    public void testCalculateOverallAverage() {
        double level5Average = 63.50;
        double level6Average = 63.67;

        double result = MethodA.calculateOverallAverage(level5Average, level6Average);
        assertEquals(63.585, result, 0.001, "Overall average calculation failed");
    }

    @Test
    public void testCalculateWeightedAverageWithMismatchedInputSizes() {
        List<Integer> credits = Arrays.asList(10, 20, 20);
        List<Integer> marks = Arrays.asList(70, 75); // Different size

        try {
            MethodA.calculateWeightedAverage(credits, marks);
        } catch (IllegalArgumentException e) {
            assertEquals("Credits and marks lists must have the same size.", e.getMessage());
        }
    }
}