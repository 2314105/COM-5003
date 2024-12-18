package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkClassifierTest {

    private final MarkClassifier markClassifier = new MarkClassifier();

    @Test
    void testGetClassification() {
        // Test each classification range
        assertEquals("1", markClassifier.getClassification(70.0));
        assertEquals("2.1", markClassifier.getClassification(65.0));
        assertEquals("2.2", markClassifier.getClassification(55.0));
        assertEquals("3rd", markClassifier.getClassification(45.0));
        assertEquals("Fail", markClassifier.getClassification(35.0));

        // Test boundary values
        assertEquals("1", markClassifier.getClassification(70.0));
        assertEquals("2.1", markClassifier.getClassification(60.0));
        assertEquals("2.2", markClassifier.getClassification(50.0));
        assertEquals("3rd", markClassifier.getClassification(40.0));
        assertEquals("Fail", markClassifier.getClassification(39.9));
    }

    @Test
    void testGetNumericValueFromClassification() {
        // Test valid classifications
        assertEquals(1, markClassifier.getNumericValueFromClassification("1"));
        assertEquals(2, markClassifier.getNumericValueFromClassification("2.1"));
        assertEquals(3, markClassifier.getNumericValueFromClassification("2.2"));
        assertEquals(4, markClassifier.getNumericValueFromClassification("3rd"));
        assertEquals(5, markClassifier.getNumericValueFromClassification("Fail"));

        // Test invalid classification
        assertEquals(Integer.MAX_VALUE, markClassifier.getNumericValueFromClassification("Unknown"));
        assertEquals(Integer.MAX_VALUE, markClassifier.getNumericValueFromClassification(""));
    }

    @Test
    void testGetClassificationFromNumericValue() {
        // Test valid numeric values
        assertEquals("1", markClassifier.getClassificationFromNumericValue(1));
        assertEquals("2.1", markClassifier.getClassificationFromNumericValue(2));
        assertEquals("2.2", markClassifier.getClassificationFromNumericValue(3));
        assertEquals("3rd", markClassifier.getClassificationFromNumericValue(4));
        assertEquals("Fail", markClassifier.getClassificationFromNumericValue(5));

        // Test invalid numeric values
        assertEquals("Unknown", markClassifier.getClassificationFromNumericValue(0));
        assertEquals("Unknown", markClassifier.getClassificationFromNumericValue(6));
    }
}
