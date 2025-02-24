package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
This class tests the functionality of the MarkClassifier class.
It ensures that marks and classifications are accurately mapped
between different representations.
*/
class MarkClassifierTest {

    // Create an instance of MarkClassifier for testing
    private final MarkClassifier markClassifier = new MarkClassifier();

    /*
    Test the classification of marks into degree categories.
    Ensures marks within specific ranges are correctly classified.
    */
    @Test
    void testGetClassification() {
        // Test each classification range
        assertEquals("1", markClassifier.getClassification(70.0), "Marks >= 70 should classify as '1'");
        assertEquals("2.1", markClassifier.getClassification(65.0), "Marks >= 60 and < 70 should classify as '2.1'");
        assertEquals("2.2", markClassifier.getClassification(55.0), "Marks >= 50 and < 60 should classify as '2.2'");
        assertEquals("3rd", markClassifier.getClassification(45.0), "Marks >= 40 and < 50 should classify as '3rd'");
        assertEquals("Fail", markClassifier.getClassification(35.0), "Marks < 40 should classify as 'Fail'");

        // Test boundary values
        assertEquals("1", markClassifier.getClassification(70.0), "Boundary value for '1'");
        assertEquals("2.1", markClassifier.getClassification(60.0), "Boundary value for '2.1'");
        assertEquals("2.2", markClassifier.getClassification(50.0), "Boundary value for '2.2'");
        assertEquals("3rd", markClassifier.getClassification(40.0), "Boundary value for '3rd'");
        assertEquals("Fail", markClassifier.getClassification(39.9), "Boundary value for 'Fail'");
    }

    /*
    Test the conversion of classification strings to numeric values.
    Ensures valid classifications map to correct numeric values and invalid ones return MAX_VALUE.
    */
    @Test
    void testGetNumericValueFromClassification() {
        // Test valid classifications
        assertEquals(1, markClassifier.getNumericValueFromClassification("1"), "'1' should map to numeric value 1");
        assertEquals(2, markClassifier.getNumericValueFromClassification("2.1"), "'2.1' should map to numeric value 2");
        assertEquals(3, markClassifier.getNumericValueFromClassification("2.2"), "'2.2' should map to numeric value 3");
        assertEquals(4, markClassifier.getNumericValueFromClassification("3rd"), "'3rd' should map to numeric value 4");
        assertEquals(5, markClassifier.getNumericValueFromClassification("Fail"), "'Fail' should map to numeric value 5");

        // Test invalid classifications
        assertEquals(Integer.MAX_VALUE, markClassifier.getNumericValueFromClassification("Unknown"), "Invalid classification should return MAX_VALUE");
        assertEquals(Integer.MAX_VALUE, markClassifier.getNumericValueFromClassification(""), "Empty classification should return MAX_VALUE");
    }

    /*
    Test the conversion of numeric values back to classification strings.
    Ensures valid numeric values map to correct classifications and invalid ones return "Unknown".
    */
    @Test
    void testGetClassificationFromNumericValue() {
        // Test valid numeric values
        assertEquals("1", markClassifier.getClassificationFromNumericValue(1), "Numeric value 1 should map to '1'");
        assertEquals("2.1", markClassifier.getClassificationFromNumericValue(2), "Numeric value 2 should map to '2.1'");
        assertEquals("2.2", markClassifier.getClassificationFromNumericValue(3), "Numeric value 3 should map to '2.2'");
        assertEquals("3rd", markClassifier.getClassificationFromNumericValue(4), "Numeric value 4 should map to '3rd'");
        assertEquals("Fail", markClassifier.getClassificationFromNumericValue(5), "Numeric value 5 should map to 'Fail'");

        // Test invalid numeric values
        assertEquals("Unknown", markClassifier.getClassificationFromNumericValue(0), "Numeric value 0 should return 'Unknown'");
        assertEquals("Unknown", markClassifier.getClassificationFromNumericValue(6), "Numeric value 6 should return 'Unknown'");
    }
}
