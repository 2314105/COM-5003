package model;

import java.util.List;

/*
This class provides validation connection for a list of modules.
It ensures that all module data meets the required standards and passes the validation rules.
*/
public class ValidationManager {

    private final Validator validator;

    /*
    Constructor to initialize the ValidationManager with a Validator instance.
    */
    public ValidationManager(Validator validator) {
        this.validator = validator;
    }

    /*
    Validates a list of modules based on predefined rules:
    - Marks must be 40% or above to pass.
    - Marks must be integers.
    - Total credits per level must add up to 120.

    If validation fails, the provided ValidationFeedback interface is used
    to notify the user with an error message.

    Returns true if all validation rules pass; otherwise, false.
    */
    public boolean validateModules(List<Module> modules, int level, ValidationFeedback feedback) {
        // Check that all marks meet the pass threshold
        if (!validator.validatePassMarks(modules)) {
            feedback.onValidationFailed("Error: All marks must be 40% or above to pass.");
            return false;
        }

        // Check that all marks are integers
        if (!validator.validateCategoricalMarks(modules)) {
            feedback.onValidationFailed("Error: Marks must be integers.");
            return false;
        }

        // Check that the total credits add up to 120 for the level
        if (!validator.validateCreditsPerLevel(modules)) {
            feedback.onValidationFailed("Error: Total credits must add up to 120.");
            return false;
        }

        // All validations passed
        return true;
    }

    /*
    Interface for handling validation feedback.
    Implementations of this interface provide a way to display or log error messages.
    */
    public interface ValidationFeedback {
        void onValidationFailed(String message);
    }
}
