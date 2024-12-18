package model;

import java.util.List;

public class ValidationService {

    private final Validator validator;

    public ValidationService(Validator validator) {
        this.validator = validator;
    }

    public boolean validateModules(List<Module> modules, int level, ValidationFeedback feedback) {
        if (!validator.validatePassMarks(modules)) {
            feedback.onValidationFailed("Error: All marks must be 40% or above to pass.");
            return false;
        }

        if (!validator.validateCategoricalMarks(modules)) {
            feedback.onValidationFailed("Error: Marks must be integers.");
            return false;
        }

        if (!validator.validateCreditsPerLevel(modules)) {
            feedback.onValidationFailed("Error: Total credits must add up to 120.");
            return false;
        }

        return true;


    }

    public interface ValidationFeedback {
        void onValidationFailed(String message);
    }
}
