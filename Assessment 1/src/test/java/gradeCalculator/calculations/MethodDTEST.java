package gradeCalculator.calculations;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodDTEST {

    @Test
    public void testCalculateMarkProfile() {
        List<Integer> credits = Arrays.asList(20, 40, 20, 10, 10, 10, 10);
        List<Integer> classifications = Arrays.asList(1, 1, 1, 2, 2, 2, 3);

        String result = MethodD.calculateMarkProfile(credits, classifications);
        assertEquals("1st", result, "Mark profiling failed");
    }
}