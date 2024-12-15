package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class Level5Level6CalculatorTest {

    private final Level5Level6Calculator calculator = new Level5Level6Calculator();

    @Test
    public void testCalculateMethodA() {
        List<Double> l5Marks = Arrays.asList(65.0, 70.0, 60.0);
        List<Integer> l5Credits = Arrays.asList(30, 30, 30);
        List<Double> l6Marks = Arrays.asList(80.0, 75.0, 70.0);
        List<Integer> l6Credits = Arrays.asList(40, 40, 40);

        double result = calculator.calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double expected = 72.5; // Calculate manually or use expected logic

        assertEquals(expected, result, 0.01); // Allowing some precision error
    }

    @Test
    public void testCalculateMethodB() {
        List<Double> l5Marks = Arrays.asList(65.0, 70.0, 60.0);
        List<Integer> l5Credits = Arrays.asList(30, 30, 30);
        List<Double> l6Marks = Arrays.asList(80.0, 75.0, 70.0);
        List<Integer> l6Credits = Arrays.asList(40, 40, 40);

        double result = calculator.calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);
        double expected = 74.0; // Calculate manually or use expected logic

        assertEquals(expected, result, 0.01); // Allowing some precision error
    }

    @Test
    public void testCalculateMethodD() {
        List<Double> l5Marks = Arrays.asList(65.0, 70.0, 60.0);
        List<Integer> l5Credits = Arrays.asList(30, 30, 30);
        List<Double> l6Marks = Arrays.asList(80.0, 75.0, 70.0);
        List<Integer> l6Credits = Arrays.asList(40, 40, 40);

        String result = calculator.calculateMethodD(l5Marks, l5Credits, l6Marks, l6Credits);
        String expected = "Average 1 (Level 5 + Level 6): 72.50\n" +
                "Average 2 (Level 5 + Level 6 x 2): 74.00\n" +
                "Higher Average Mark: 74.00\n" +
                "Classification (Method A): 2:1\n" +
                "Classification (Method B): 2:1\n" +
                "Profile Mark Classification: Lower Classification\n";

        assertEquals(expected, result);
    }

    @Test
    public void testGetClassification() {
        assertEquals("1st", calculator.getClassification(75.0));
        assertEquals("2:1", calculator.getClassification(65.0));
        assertEquals("2:2", calculator.getClassification(55.0));
        assertEquals("3rd", calculator.getClassification(45.0));
        assertEquals("Fail", calculator.getClassification(35.0));
    }

    @Test
    public void testGetProfileMarkClassification() {
        List<Double> l5Marks = Arrays.asList(75.0, 80.0, 60.0);
        List<Integer> l5Credits = Arrays.asList(30, 30, 30);
        List<Double> l6Marks = Arrays.asList(70.0, 85.0, 65.0);
        List<Integer> l6Credits = Arrays.asList(40, 40, 40);

        String result = calculator.getProfileMarkClassification(l5Marks, l5Credits, l6Marks, l6Credits);
        assertEquals("First Class", result);

        List<Double> l5MarksLower = Arrays.asList(60.0, 60.0, 50.0);
        List<Integer> l5CreditsLower = Arrays.asList(30, 30, 30);
        List<Double> l6MarksLower = Arrays.asList(55.0, 50.0, 45.0);
        List<Integer> l6CreditsLower = Arrays.asList(40, 40, 40);

        result = calculator.getProfileMarkClassification(l5MarksLower, l5CreditsLower, l6MarksLower, l6CreditsLower);
        assertEquals("Lower Classification", result);
    }
}