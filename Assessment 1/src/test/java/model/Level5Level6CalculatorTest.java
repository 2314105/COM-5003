package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
This class tests the functionality of the Level5Level6Calculator.
It ensures that the calculation methods produce accurate results
for various scenarios of Level 5 and Level 6 data.
*/
class Level5Level6CalculatorTest {

    // Create an instance of the calculator for testing
    private final Level5Level6Calculator calculator = new Level5Level6Calculator();

    /*
    Test case: Scenario 1 - Tests a balanced distribution of Level 5 and Level 6 marks and credits.
    Verifies the results of Method A, Method B, and Method D.
    */
    @Test
    void testScenario1() {
        // Arrange: Define Level 5 and Level 6 data for Student A
        List<Double> l5Marks = Arrays.asList(78.0, 75.0, 78.0, 75.0, 78.0, 68.0);
        List<Integer> l5Credits = Arrays.asList(20, 20, 20, 20, 20, 20);

        List<Double> l6Marks = Arrays.asList(68.0, 65.0, 62.0, 68.0, 65.0, 62.0);
        List<Integer> l6Credits = Arrays.asList(20, 20, 20, 20, 20, 20);

        // Act: Calculate results using the calculator methods
        double methodAResult = calculator.calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculator.calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);
        String methodDResult = calculator.calculateMethodD(l5Marks, l5Credits, l6Marks, l6Credits);

        // Assert: Verify the results
        assertEquals(70.17, methodAResult, 0.01); // Verify Method A
        assertEquals(68.44, methodBResult, 0.01); // Verify Method B
        assertEquals(
                "Method A - Average 1 (Level 5 + Level 6): 70.17 (1)\n" +
                        "Method B - Average 2 (Level 5 + Level 6 x2): 68.44 (2.1)\n" +
                        "Method D - Profile Mark Classification: 2.1\n" +
                        "Resulting Classification: 1",
                methodDResult // Verify Method D
        );
    }

    /*
    Test case: Scenario 2 - Tests uneven distribution with higher marks in Level 6.
    Verifies the results of Method A, Method B, and Method D.
    */
    @Test
    void testScenario2() {
        // Arrange: Define Level 5 and Level 6 data for Student B
        List<Double> l5Marks = Arrays.asList(68.0, 65.0, 62.0, 68.0);
        List<Integer> l5Credits = Arrays.asList(30, 30, 30, 30);

        List<Double> l6Marks = Arrays.asList(78.0, 75.0, 68.0, 68.0);
        List<Integer> l6Credits = Arrays.asList(30, 30, 30, 30);

        // Act: Calculate results using the calculator methods
        double methodAResult = calculator.calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculator.calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);
        String methodDResult = calculator.calculateMethodD(l5Marks, l5Credits, l6Marks, l6Credits);

        // Assert: Verify the results
        assertEquals(69.00, methodAResult, 0.01); // Verify Method A
        assertEquals(70.08, methodBResult, 0.01); // Verify Method B
        assertEquals(
                "Method A - Average 1 (Level 5 + Level 6): 69.00 (2.1)\n" +
                        "Method B - Average 2 (Level 5 + Level 6 x2): 70.08 (1)\n" +
                        "Method D - Profile Mark Classification: 2.1\n" +
                        "Resulting Classification: 1",
                methodDResult // Verify Method D
        );
    }

    /*
    Test case: Scenario 3 - Tests a mix of low, average, and high marks.
    Verifies the results of Method A, Method B, and Method D.
    */
    @Test
    void testScenario3() {
        // Arrange: Define Level 5 and Level 6 data for Student C
        List<Double> l5Marks = Arrays.asList(55.0, 52.0, 55.0, 52.0, 55.0);
        List<Integer> l5Credits = Arrays.asList(15, 15, 30, 30, 30);

        List<Double> l6Marks = Arrays.asList(55.0, 52.0, 68.0, 65.0, 62.0);
        List<Integer> l6Credits = Arrays.asList(15, 15, 30, 30, 30);

        // Act: Calculate results using the calculator methods
        double methodAResult = calculator.calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculator.calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);
        String methodDResult = calculator.calculateMethodD(l5Marks, l5Credits, l6Marks, l6Credits);

        // Assert: Verify the results
        assertEquals(58.00, methodAResult, 0.01); // Verify Method A
        assertEquals(59.38, methodBResult, 0.01); // Verify Method B
        assertEquals(
                "Method A - Average 1 (Level 5 + Level 6): 58.00 (2.2)\n" +
                        "Method B - Average 2 (Level 5 + Level 6 x2): 59.38 (2.2)\n" +
                        "Method D - Profile Mark Classification: 2.1\n" +
                        "Resulting Classification: 2.1",
                methodDResult // Verify Method D
        );
    }
}
