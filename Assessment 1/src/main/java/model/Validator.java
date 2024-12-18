package model;

import java.util.List;

public class Validator {

    // Validate that all marks are 40% or above
    public boolean validatePassMarks(List<Module> modules) {
        for (Module module : modules) {
            if (module.getMarks() < 40) {
                return false; // Fail validation if any mark is below 40
            }
        }
        return true;
    }

    // Validate that all marks are integers
    public boolean validateCategoricalMarks(List<Module> modules) {
        for (Module module : modules) {
            double marks = module.getMarks();
            if (marks != Math.floor(marks)) {
                return false; // Fail validation if mark is not an integer
            }
        }
        return true;
    }

    // Validate that total credits for a level add up to 120
    public boolean validateCreditsPerLevel(List<Module> modules) {
        int totalCredits = modules.stream().mapToInt(Module::getCredits).sum();
        return totalCredits == 120; // Valid if total credits equal 120
    }

    // Validate that module codes align with their respective levels
    public boolean validateModuleCodes(List<Module> modules, int level) {
        for (Module module : modules) {
            String moduleCode = module.getCode();
            if (moduleCode == null || moduleCode.isEmpty() || moduleCode.charAt(0) - '0' != level) {
                return false; // Fail validation if module code's first digit does not match the level
            }
        }
        return true;
    }
}
