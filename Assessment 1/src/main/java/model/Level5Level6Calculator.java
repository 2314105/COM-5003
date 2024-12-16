package model;

import java.util.List;

public class Level5Level6Calculator {

    // Method A: The average mark of all module marks achieved at Level 5 and Level 6
    public double calculateMethodA(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double l5Average = calculateWeightedAverage(l5Marks, l5Credits);
        double l6Average = calculateWeightedAverage(l6Marks, l6Credits);

        // Calculate overall average (Method A)
        return (l5Average + l6Average) / 2;
    }

    // Method B: The average mark of all module marks achieved at Level 5 and Level 6, weighted 2:1 for Level 6
    public double calculateMethodB(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double l5Average = calculateWeightedAverage(l5Marks, l5Credits);
        double l6Average = calculateWeightedAverage(l6Marks, l6Credits);

        // Calculate overall average (Method B) with Level 6 weighted more
        return (l5Average + 2 * l6Average) / 3;
    }

    // Helper method to calculate the weighted average for marks and credits
    private double calculateWeightedAverage(List<Double> marks, List<Integer> credits) {
        double weightedSum = 0;
        int totalCredits = 0;

        // Calculate weighted sum and total credits
        for (int i = 0; i < marks.size(); i++) {
            weightedSum += marks.get(i) * credits.get(i);
            totalCredits += credits.get(i);
        }

        // Return the weighted average
        return weightedSum / totalCredits;
    }

    // Method D: Mark profiling methods A and B
    public String calculateMethodD(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double methodAResult = calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);

        // Determine classifications based on the weighted average marks
        String classificationA = getClassification(methodAResult);
        String classificationB = getClassification(methodBResult);

        // Profile Mark Calculation based on classification and credits
        String profileMarkClassification = getProfileMarkClassification(l5Marks, l5Credits, l6Marks, l6Credits);

        // Return formatted result string
        return formatResults(methodAResult, methodBResult, classificationA, classificationB, profileMarkClassification);
    }

    // Helper method to determine classification based on mark
    protected String getClassification(double mark) {
        if (mark >= 70) {
            return "1st";
        } else if (mark >= 60) {
            return "2:1";
        } else if (mark >= 50) {
            return "2:2";
        } else if (mark >= 40) {
            return "3rd";
        } else {
            return "Fail";
        }
    }

    // Helper method to calculate profile mark classification based on the total credits in each classification
    protected String getProfileMarkClassification(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        int totalCredits = 0;
        int higherClassCredits = 0;

        // Calculate Level 5 credit classifications
        for (int i = 0; i < l5Marks.size(); i++) {
            int credits = l5Credits.get(i);
            if (getClassification(l5Marks.get(i)).equals("First Class")) {
                higherClassCredits += credits;
            }
            totalCredits += credits;
        }

        // Calculate Level 6 credit classifications with double weighting
        for (int i = 0; i < l6Marks.size(); i++) {
            int credits = l6Credits.get(i) * 2;  // Double weighting for Level 6
            if (getClassification(l6Marks.get(i)).equals("First Class")) {
                higherClassCredits += credits;
            }
            totalCredits += credits;
        }

        // If 50% or more of credits are in a higher classification, return First Class
        if ((double) higherClassCredits / totalCredits >= 0.5) {
            return "First Class";
        } else {
            return "Lower Classification";
        }
    }

    // Method to format the results
    private String formatResults(double methodAResult, double methodBResult, String classificationA, String classificationB, String profileMarkClassification) {
        StringBuilder result = new StringBuilder();
        result.append("Average 1 (Level 5 + Level 6): ").append(String.format("%.2f", methodAResult)).append("\n");
        result.append("Average 2 (Level 5 + Level 6 x 2): ").append(String.format("%.2f", methodBResult)).append("\n");
        result.append("Higher Average Mark: ").append(String.format("%.2f", Math.max(methodAResult, methodBResult))).append("\n");
        result.append("Profile Mark Classification: ").append(profileMarkClassification).append("\n");
        return result.toString();
    }
}