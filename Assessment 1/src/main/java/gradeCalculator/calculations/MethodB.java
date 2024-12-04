package gradeCalculator.calculations;

import java.util.List;

public class MethodB {
    public static double calculateOverallAverage(double l5Average, double l6Average) {
        return (l5Average + l6Average + l6Average) / 3;
    }

    public static double calculateWeightedAverage(List<Integer> credits, List<Integer> marks) {
        if (credits.size() != marks.size()) {
            throw new IllegalArgumentException("Credits and marks lists must have the same size.");
        }

        int totalCredits = 0;
        int weightedSum = 0;

        for (int i = 0; i < credits.size(); i++) {
            totalCredits += credits.get(i);
            weightedSum += credits.get(i) * marks.get(i);
        }

        return (double) weightedSum / totalCredits;
    }
}