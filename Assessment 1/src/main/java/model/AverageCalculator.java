package model;

import java.util.List;

public class AverageCalculator {

    public static double calculateAverage(List<Module> modules) {
        double weightedSum = 0;
        int totalCredits = 0;

        for (Module module : modules) {
            int credits = module.getCredits();
            double marks = module.getMarks();
            weightedSum += credits * marks;
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            return 0;
        }

        // Do not round here; keep full precision until later
        return weightedSum / totalCredits;
    }
}
