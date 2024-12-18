package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Level5Level6CalculatorTest {

    private final Level5Level6Calculator calculator = new Level5Level6Calculator();

    @Test
    void testScenario1() {
        // Student A's Level 5 data
        List<Double> l5Marks = Arrays.asList(78.0, 75.0, 78.0, 75.0, 78.0, 68.0);
        List<Integer> l5Credits = Arrays.asList(20, 20, 20, 20, 20, 20);

        // Student A's Level 6 data
        List<Double> l6Marks = Arrays.asList(68.0, 65.0, 62.0, 68.0, 65.0, 62.0);
        List<Integer> l6Credits = Arrays.asList(20, 20, 20, 20, 20, 20);

        double methodAResult = calculator.calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculator.calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);
        String methodDResult = calculator.calculateMethodD(l5Marks, l5Credits, l6Marks, l6Credits);

        assertEquals(70.17, methodAResult, 0.01);
        assertEquals(68.44, methodBResult, 0.01);
        assertEquals("Method A - Average 1 (Level 5 + Level 6): 70.17 (1)\nMethod B - Average 2 (Level 5 + Level 6 x2): 68.44 (2.1)\nMethod D - Profile Mark Classification: 2.1\nResulting Classification: 1", methodDResult);
    }

    @Test
    void testScenario2() {
        // Student B's Level 5 data
        List<Double> l5Marks = Arrays.asList(68.0, 65.0, 62.0, 68.0);
        List<Integer> l5Credits = Arrays.asList(30, 30, 30, 30);

        // Student B's Level 6 data
        List<Double> l6Marks = Arrays.asList(78.0, 75.0, 68.0, 68.0);
        List<Integer> l6Credits = Arrays.asList(30, 30, 30, 30);

        double methodAResult = calculator.calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculator.calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);
        String methodDResult = calculator.calculateMethodD(l5Marks, l5Credits, l6Marks, l6Credits);

        assertEquals(69.00, methodAResult, 0.01);
        assertEquals(70.08, methodBResult, 0.01);
        assertEquals("Method A - Average 1 (Level 5 + Level 6): 69.00 (2.1)\nMethod B - Average 2 (Level 5 + Level 6 x2): 70.08 (1)\nMethod D - Profile Mark Classification: 2.1\nResulting Classification: 1", methodDResult);
    }

    @Test
    void testScenario3() {
        // Student C's Level 5 data
        List<Double> l5Marks = Arrays.asList(55.0, 52.0, 55.0, 52.0, 55.0);
        List<Integer> l5Credits = Arrays.asList(15, 15, 30, 30, 30);

        // Student C's Level 6 data
        List<Double> l6Marks = Arrays.asList(55.0, 52.0, 68.0, 65.0, 62.0);
        List<Integer> l6Credits = Arrays.asList(15, 15, 30, 30, 30);

        double methodAResult = calculator.calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculator.calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);
        String methodDResult = calculator.calculateMethodD(l5Marks, l5Credits, l6Marks, l6Credits);

        assertEquals(58.00, methodAResult, 0.01);
        assertEquals(59.38, methodBResult, 0.01);
        assertEquals("Method A - Average 1 (Level 5 + Level 6): 58.00 (2.2)\nMethod B - Average 2 (Level 5 + Level 6 x2): 59.38 (2.2)\nMethod D - Profile Mark Classification: 2.1\nResulting Classification: 2.1", methodDResult);
    }
}