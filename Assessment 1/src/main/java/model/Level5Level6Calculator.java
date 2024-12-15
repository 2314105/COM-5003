package model;

import java.util.List;

public class Level5Level6Calculator {

    // Method A: The average mark of all module marks achieved at Level 5 and Level 6
    public double calculateMethodA(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double weightedL5Sum = 0;
        double weightedL6Sum = 0;
        int totalL5Credits = 0;
        int totalL6Credits = 0;

        // Calculate weighted sums and total credits for Level 5
        for (int i = 0; i < l5Marks.size(); i++) {
            weightedL5Sum += l5Marks.get(i) * l5Credits.get(i);
            totalL5Credits += l5Credits.get(i);
        }

        // Calculate weighted sums and total credits for Level 6
        for (int i = 0; i < l6Marks.size(); i++) {
            weightedL6Sum += l6Marks.get(i) * l6Credits.get(i);
            totalL6Credits += l6Credits.get(i);
        }

        // Calculate average for Level 5 and Level 6
        double l5Average = weightedL5Sum / totalL5Credits;
        double l6Average = weightedL6Sum / totalL6Credits;

        // Calculate overall average (Method A)
        return (l5Average + l6Average) / 2;
    }

    // Method B: The average mark of all module marks achieved at Level 5 and Level 6, weighted 2:1 for Level 6
    public double calculateMethodB(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double weightedL5Sum = 0;
        double weightedL6Sum = 0;
        int totalL5Credits = 0;
        int totalL6Credits = 0;

        // Calculate weighted sums and total credits for Level 5
        for (int i = 0; i < l5Marks.size(); i++) {
            weightedL5Sum += l5Marks.get(i) * l5Credits.get(i);
            totalL5Credits += l5Credits.get(i);
        }

        // Calculate weighted sums and total credits for Level 6
        for (int i = 0; i < l6Marks.size(); i++) {
            weightedL6Sum += l6Marks.get(i) * l6Credits.get(i);
            totalL6Credits += l6Credits.get(i);
        }

        // Calculate average for Level 5 and Level 6, weighted in favor of Level 6
        double l5Average = weightedL5Sum / totalL5Credits;
        double l6Average = weightedL6Sum / totalL6Credits;

        // Calculate overall average (Method B) with Level 6 weighted more
        return (l5Average + 2 * l6Average) / 3;
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
    private String getClassification(double mark) {
        if (mark >= 70) {
            return "First Class";
        } else if (mark >= 60) {
            return "2:1";
        } else if (mark >= 50) {
            return "2:2";
        } else if (mark >= 40) {
            return "Third Class";
        } else {
            return "Fail";
        }
    }

    // Helper method to calculate profile mark classification based on the total credits in each classification
    private String getProfileMarkClassification(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
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
        result.append("Average 1 (Level 5 + Level 6): ").append(String.format("%.2f", methodAResult)).append("\n\n");
        result.append("Average 2 (Level 5 + Level 6 x 2): ").append(String.format("%.2f", methodBResult)).append("\n\n");
        result.append("Higher Average Mark: ").append(String.format("%.2f", Math.max(methodAResult, methodBResult))).append("\n\n");
        result.append("Profile Mark Classification: ").append(profileMarkClassification).append("\n");

        return result.toString();
    }
}