package gradeCalculator.calculations;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodBTEST {

    @Test
    public void testCalculateWeightedAverage() {
        List<Integer> credits = Arrays.asList(10, 20, 20, 20, 10, 20, 20);
        List<Integer> marks = Arrays.asList(70, 70, 75, 50, 76, 58, 55);

        double result = MethodB.calculateWeightedAverage(credits, marks);
        assertEquals(63.50, result, 0.01, "Weighted average calculation failed");
    }

    @Test
    public void testCalculateOverallAverage() {
        double level5Average = 63.50;
        double level6Average = 63.67;

        double result = MethodB.calculateOverallAverage(level5Average, level6Average);
        assertEquals(63.61, result, 0.01, "Overall average calculation failed");
    }
}