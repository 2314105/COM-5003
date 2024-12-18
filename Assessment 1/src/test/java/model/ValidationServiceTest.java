package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationServiceTest {

    private ValidationService validationService;
    private MockValidator mockValidator;
    private TestValidationFeedback feedback;

    @BeforeEach
    void setUp() {
        mockValidator = new MockValidator();
        validationService = new ValidationService(mockValidator);
        feedback = new TestValidationFeedback();
    }

    @Test
    void testValidateModulesPassMarksFail() {
        // Arrange
        List<Module> modules = List.of(
                new Module("Module1", 30, 35), // Fails pass marks
                new Module("Module2", 30, 50)
        );
        mockValidator.setPassMarksValid(false);

        // Act
        boolean isValid = validationService.validateModules(modules, 5, feedback);

        // Assert
        assertFalse(isValid);
        assertTrue(feedback.getLastMessage().contains("Error: All marks must be 40% or above to pass."));
    }

    @Test
    void testValidateModulesCategoricalMarksFail() {
        // Arrange
        List<Module> modules = List.of(
                new Module("Module1", 30, 45.5) // Fails categorical marks
        );
        mockValidator.setCategoricalMarksValid(false);

        // Act
        boolean isValid = validationService.validateModules(modules, 5, feedback);

        // Assert
        assertFalse(isValid);
        assertTrue(feedback.getLastMessage().contains("Error: Marks must be integers."));
    }

    @Test
    void testValidateModulesCreditsPerLevelFail() {
        // Arrange
        List<Module> modules = List.of(
                new Module("Module1", 30, 50),
                new Module("Module2", 30, 60) // Total credits < 120
        );
        mockValidator.setCreditsPerLevelValid(false);

        // Act
        boolean isValid = validationService.validateModules(modules, 5, feedback);

        // Assert
        assertFalse(isValid);
        assertTrue(feedback.getLastMessage().contains("Error: Total credits must add up to 120."));
    }

    @Test
    void testValidateModulesAllValid() {
        // Arrange
        List<Module> modules = List.of(
                new Module("Module1", 60, 70),
                new Module("Module2", 60, 80)
        );
        mockValidator.setPassMarksValid(true);
        mockValidator.setCategoricalMarksValid(true);
        mockValidator.setCreditsPerLevelValid(true);

        // Act
        boolean isValid = validationService.validateModules(modules, 5, feedback);

        // Assert
        assertTrue(isValid);
        assertTrue(feedback.getLastMessage().isEmpty());
    }

    @Test
    void testValidateModulesMultipleFailures() {
        // Arrange
        List<Module> modules = List.of(
                new Module("Module1", 30, 35), // Fails pass marks
                new Module("Module2", 30, 45.5) // Fails categorical marks
        );
        mockValidator.setPassMarksValid(false);
        mockValidator.setCategoricalMarksValid(false);

        // Act
        boolean isValid = validationService.validateModules(modules, 5, feedback);

        // Assert
        assertFalse(isValid);
        // Ensure the first failure message is captured
        assertTrue(feedback.getLastMessage().contains("Error: All marks must be 40% or above to pass."));
    }

    // Mock Validator Class for Testing
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

    // Test Implementation of ValidationFeedback
    private static class TestValidationFeedback implements ValidationService.ValidationFeedback {
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
