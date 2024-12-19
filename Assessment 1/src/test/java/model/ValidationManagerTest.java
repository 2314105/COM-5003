package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
This class tests the ValidationManager class, ensuring that it correctly validates
module data under different scenarios by using mock dependencies for isolation.
*/
class ValidationManagerTest {

    private ValidationManager validationManager; // The class under test
    private MockValidator mockValidator; // Mock implementation of the Validator class
    private TestValidationFeedback feedback; // Mock implementation for capturing feedback

    /*
    Sets up the test environment before each test, initializing the mock dependencies
    and the ValidationManager instance.
    */
    @BeforeEach
    void setUp() {
        mockValidator = new MockValidator();
        validationManager = new ValidationManager(mockValidator);
        feedback = new TestValidationFeedback();
    }

    /*
    Tests validation failure when one or more modules fail the pass marks criteria (marks < 40).
    */
    @Test
    void testValidateModulesPassMarksFail() {
        // Arrange: Create a list of modules where one fails the pass marks validation
        List<Module> modules = List.of(
                new Module("Module1", 30, 35), // Fails pass marks
                new Module("Module2", 30, 50)
        );
        mockValidator.setPassMarksValid(false); // Simulate pass marks validation failure

        // Act: Validate the modules
        boolean isValid = validationManager.validateModules(modules, 5, feedback);

        // Assert: Validation should fail, and feedback should contain the appropriate error message
        assertFalse(isValid);
        assertTrue(feedback.getLastMessage().contains("Error: All marks must be 40% or above to pass."));
    }

    /*
    Tests validation failure when one or more modules have non-integer marks.
    */
    @Test
    void testValidateModulesCategoricalMarksFail() {
        // Arrange: Create a list of modules where one has non-integer marks
        List<Module> modules = List.of(
                new Module("Module1", 30, 45.5) // Fails categorical marks validation
        );
        mockValidator.setCategoricalMarksValid(false); // Simulate categorical marks validation failure

        // Act: Validate the modules
        boolean isValid = validationManager.validateModules(modules, 5, feedback);

        // Assert: Validation should fail, and feedback should contain the appropriate error message
        assertFalse(isValid);
        assertTrue(feedback.getLastMessage().contains("Error: Marks must be integers."));
    }

    /*
    Tests validation failure when the total credits across all modules do not sum to 120.
    */
    @Test
    void testValidateModulesCreditsPerLevelFail() {
        // Arrange: Create a list of modules where total credits are less than 120
        List<Module> modules = List.of(
                new Module("Module1", 30, 50),
                new Module("Module2", 30, 60) // Total credits < 120
        );
        mockValidator.setCreditsPerLevelValid(false); // Simulate credits validation failure

        // Act: Validate the modules
        boolean isValid = validationManager.validateModules(modules, 5, feedback);

        // Assert: Validation should fail, and feedback should contain the appropriate error message
        assertFalse(isValid);
        assertTrue(feedback.getLastMessage().contains("Error: Total credits must add up to 120."));
    }

    /*
    Tests successful validation when all modules meet the criteria.
    */
    @Test
    void testValidateModulesAllValid() {
        // Arrange: Create a list of valid modules
        List<Module> modules = List.of(
                new Module("Module1", 60, 70),
                new Module("Module2", 60, 80)
        );
        mockValidator.setPassMarksValid(true);
        mockValidator.setCategoricalMarksValid(true);
        mockValidator.setCreditsPerLevelValid(true);

        // Act: Validate the modules
        boolean isValid = validationManager.validateModules(modules, 5, feedback);

        // Assert: Validation should pass, and feedback should be empty
        assertTrue(isValid);
        assertTrue(feedback.getLastMessage().isEmpty());
    }

    /*
    Tests validation failure when multiple criteria fail. Ensures the feedback
    captures the first failure message.
    */
    @Test
    void testValidateModulesMultipleFailures() {
        // Arrange: Create a list of modules failing multiple criteria
        List<Module> modules = List.of(
                new Module("Module1", 30, 35), // Fails pass marks
                new Module("Module2", 30, 45.5) // Fails categorical marks
        );
        mockValidator.setPassMarksValid(false);
        mockValidator.setCategoricalMarksValid(false);

        // Act: Validate the modules
        boolean isValid = validationManager.validateModules(modules, 5, feedback);

        // Assert: Validation should fail, and feedback should capture the first failure message
        assertFalse(isValid);
        assertTrue(feedback.getLastMessage().contains("Error: All marks must be 40% or above to pass."));
    }

    /*
    Mock implementation of the Validator class, used to simulate validation behavior
    without relying on the real implementation.
    */
    private static class MockValidator extends Validator {
        private boolean passMarksValid = true;
        private boolean categoricalMarksValid = true;
        private boolean creditsPerLevelValid = true;

        void setPassMarksValid(boolean isValid) {
            this.passMarksValid = isValid;
        }

        void setCategoricalMarksValid(boolean isValid) {
            this.categoricalMarksValid = isValid;
        }

        void setCreditsPerLevelValid(boolean isValid) {
            this.creditsPerLevelValid = isValid;
        }

        @Override
        public boolean validatePassMarks(List<Module> modules) {
            return passMarksValid;
        }

        @Override
        public boolean validateCategoricalMarks(List<Module> modules) {
            return categoricalMarksValid;
        }

        @Override
        public boolean validateCreditsPerLevel(List<Module> modules) {
            return creditsPerLevelValid;
        }
    }

    /*
    Mock implementation of the ValidationFeedback interface.
    Captures feedback messages for verification in tests.
    */
    private static class TestValidationFeedback implements ValidationManager.ValidationFeedback {
        private String lastMessage = "";

        @Override
        public void onValidationFailed(String message) {
            lastMessage = message;
        }

        String getLastMessage() {
            return lastMessage;
        }
    }
}
