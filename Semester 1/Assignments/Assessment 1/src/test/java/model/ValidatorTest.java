package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
This class tests the Validator class, ensuring that its methods correctly validate
module data based on pass marks, categorical marks, and total credits.
*/
class ValidatorTest {

    private final Validator validator = new Validator(); // Instance of the Validator class

    /*
    Tests the validatePassMarks method to ensure it correctly identifies modules
    where all marks are above the passing threshold of 40%.
    */
    @Test
    void testValidatePassMarks() {
        // Arrange: Create a list of modules with valid marks
        List<Module> modules = List.of(
                new Module("COM5015", 30, 45),
                new Module("COM5020", 40, 50)
        );

        // Act & Assert: Validation should pass for valid marks
        assertTrue(validator.validatePassMarks(modules));

        // Arrange: Create a list of modules where one module fails the pass marks validation
        modules = List.of(
                new Module("COM5015", 30, 35), // Failing mark
                new Module("COM5020", 40, 50)
        );

        // Act & Assert: Validation should fail for failing marks
        assertFalse(validator.validatePassMarks(modules));
    }

    /*
    Tests the validateCategoricalMarks method to ensure it correctly identifies modules
    where all marks are integers (no fractional parts).
    */
    @Test
    void testValidateCategoricalMarks() {
        // Arrange: Create a list of modules with integer marks
        List<Module> modules = List.of(
                new Module("COM5015", 30, 45),
                new Module("COM5020", 40, 50)
        );

        // Act & Assert: Validation should pass for integer marks
        assertTrue(validator.validateCategoricalMarks(modules));

        // Arrange: Create a list of modules where one module has non-integer marks
        modules = List.of(
                new Module("COM5015", 30, 45.5) // Non-integer mark
        );

        // Act & Assert: Validation should fail for non-integer marks
        assertFalse(validator.validateCategoricalMarks(modules));
    }

    /*
    Tests the validateCreditsPerLevel method to ensure it correctly validates
    that the total credits for a set of modules add up to 120.
    */
    @Test
    void testValidateCreditsPerLevel() {
        // Arrange: Create a list of modules where total credits equal 120
        List<Module> modules = List.of(
                new Module("COM5015", 30, 45),
                new Module("COM5020", 40, 50),
                new Module("COM5030", 50, 60)
        );

        // Act & Assert: Validation should pass for valid total credits
        assertTrue(validator.validateCreditsPerLevel(modules));

        // Arrange: Create a list of modules where total credits do not equal 120
        modules = List.of(
                new Module("COM5015", 30, 45),
                new Module("COM5020", 40, 50) // Total credits = 70
        );

        // Act & Assert: Validation should fail for invalid total credits
        assertFalse(validator.validateCreditsPerLevel(modules));
    }
}
