package model;

import java.util.List;

public class DegreeCalculator {

    // Method A: Calculate simple average (sum of marks / number of modules)
    public static double calculateSimpleAverage(List<Double> marks) {
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum / marks.size();
    }

    // Method B: Calculate weighted average (using credits)
    public static double calculateWeightedAverage(List<Integer> credits, List<Double> marks) {
        double totalCredits = 0;
        double weightedSum = 0;

        if (credits.size() != marks.size()) {
            throw new IllegalArgumentException("Credits and Marks lists must have the same length.");
        }

        for (int i = 0; i < credits.size(); i++) {
            totalCredits += credits.get(i);
            weightedSum += credits.get(i) * marks.get(i);
        }

        return weightedSum / totalCredits;
    }

    // Method C: Calculate degree classification (based on weighted average)
    public static String calculateDegreeClassification(double weightedAverage) {
        if (weightedAverage >= 70) {
            return "First Class";
        } else if (weightedAverage >= 60) {
            return "Upper Second Class (2:1)";
        } else if (weightedAverage >= 50) {
            return "Lower Second Class (2:2)";
        } else if (weightedAverage >= 40) {
            return "Third Class";
        } else {
            return "Fail";
        }
    }

    /*
    // Method D: Calculate weighted average using credits and marks (already provided)
    public static double calculateWeightedAverage(List<Integer> credits, List<Double> marks) {
        double totalCredits = 0;
        double weightedSum = 0;

        if (credits.size() != marks.size()) {
            throw new IllegalArgumentException("Credits and Marks lists must have the same length.");
        }

        for (int i = 0; i < credits.size(); i++) {
            totalCredits += credits.get(i);
            weightedSum += credits.get(i) * marks.get(i);
        }

        return weightedSum / totalCredits;
    }

     */

    // Method to generate result string for display
    public static String generateResultsString(List<String> modules, List<Integer> credits, List<Double> marks, double average, String classification) {
        StringBuilder result = new StringBuilder("Modules and Marks:\n");

        for (int i = 0; i < modules.size(); i++) {
            result.append(modules.get(i))
                    .append(" - Credits: ").append(credits.get(i))
                    .append(", Marks: ").append(marks.get(i)).append("\n");
        }

        result.append("\nWeighted Average: ").append(String.format("%.2f", average))
                .append("\nDegree Classification: ").append(classification);

        return result.toString();
    }
}