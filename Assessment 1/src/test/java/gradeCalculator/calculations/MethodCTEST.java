package gradeCalculator.calculations;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodCTEST {

    @Test
    public void testCalculateLevel6Average() {
        List<Integer> credits = Arrays.asList(20, 40, 20, 10, 10, 10, 10);
        List<Integer> marks = Arrays.asList(65, 70, 70, 70, 58, 51, 35);

        double result = MethodC.calculateLevel6Average(credits, marks);
        assertEquals(63.67, result, 0.01, "Level 6 average calculation failed");
    }
}