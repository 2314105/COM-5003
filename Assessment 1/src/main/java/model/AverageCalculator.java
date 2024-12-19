package model;

import java.util.List;

    /*
    This class calculates the weighted average of marks for a list of modules.
    */
public class AverageCalculator {

    /*
    Calculates the weighted average of marks.
    Accepts a list of modules, each containing credits and marks, and computes
    the average weighted by the module credits. Returns 0 if no credits are provided.
     */
    public static double calculateAverage(List<Module> modules) {
        double weightedSum = 0;
        int totalCredits = 0;

        // Iterate through the modules to calculate the total weighted marks and credits
        for (Module module : modules) {
            weightedSum += module.getCredits() * module.getMarks();
            totalCredits += module.getCredits();
        }

        // If no credits are available, return 0 to avoid division by zero
        if (totalCredits == 0) {
            return 0;
        }

        // Calculate and return the weighted average
        return weightedSum / totalCredits;
    }
}
