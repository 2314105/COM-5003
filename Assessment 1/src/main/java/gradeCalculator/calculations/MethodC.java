package gradeCalculator.calculations;

import java.util.List;

public class MethodC {
    public static double calculateLevel6Average(List<Integer> credits, List<Integer> marks) {
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