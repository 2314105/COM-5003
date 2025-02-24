package model;

import java.util.List;

/*
This class provides methods to validate module data based on specific criteria.
The validation rules ensure data integrity and consistency for academic calculations.
*/
public class Validator {

    /*
    Validates that all marks are 40% or above.
    Returns false if any module has marks below 40, indicating a failing grade.
    */
    public boolean validatePassMarks(List<Module> modules) {
        for (Module module : modules) {
            if (module.getMarks() < 40) {
                return false; // Fail validation if any mark is below 40
            }
        }
        return true;
    }

    /*
    Validates that all marks are integers.
    Checks if the marks for each module have no fractions.
    Returns false if any mark is not an integer.
    */
    public boolean validateCategoricalMarks(List<Module> modules) {
        for (Module module : modules) {
            double marks = module.getMarks();
            if (marks != Math.floor(marks)) {
                return false; // Fail validation if mark is not an integer
            }
        }
        return true;
    }

    /*
    Validates that the total credits for a level add up to 120.
    Ensures that the sum of credits across all modules is exactly 120.
    Returns false if the total is not 120.
    */
    public boolean validateCreditsPerLevel(List<Module> modules) {
        int totalCredits = modules.stream().mapToInt(Module::getCredits).sum();
        return totalCredits == 120; // Valid if total credits equal 120
    }
}
