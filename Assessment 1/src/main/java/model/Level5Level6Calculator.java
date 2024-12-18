package model;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

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

        // Calculate averages for Level 5 and Level 6
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

        // Calculate averages for Level 5 and Level 6
        double l5Average = weightedL5Sum / totalL5Credits;
        double l6Average = weightedL6Sum / totalL6Credits;

        // Calculate overall average (Method B)
        return (l5Average + 2 * l6Average) / 3;
    }

    // Method D: Mark profiling methods A and B
    public String calculateMethodD(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double methodAResult = calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);

        // Profile the marks by checking classifications based on total credits
        String profileMarkClassification = getProfileMarkClassification(l5Marks, l5Credits, l6Marks, l6Credits);

        // Convert Method A, Method B, and Profile Mark classifications to numeric values
        int methodANumericValue = getNumericValueFromClassification(getClassification(methodAResult));
        int methodBNumericValue = getNumericValueFromClassification(getClassification(methodBResult));
        int profileNumericValue = getNumericValueFromClassification(profileMarkClassification);

        // Store the numeric values in a list for sorting
        List<Integer> numericResults = Arrays.asList(methodANumericValue, methodBNumericValue, profileNumericValue);

        // Sort the numeric values to pick the best classification (smallest number is best)
        Collections.sort(numericResults);

        // The best classification will be the one corresponding to the smallest number
        int bestNumericResult = numericResults.get(0);

        // Convert the best numeric result back to the classification string
        String finalClassification = getClassificationFromNumericValue(bestNumericResult);

        // Format the results
        return String.format(
                "Method A - Average 1 (Level 5 + Level 6): %.2f (%s)\nMethod B - Average 2 (Level 5 + Level 6 x2): %.2f (%s)\nMethod D - Profile Mark Classification: %s\nResulting Classification: %s",
                methodAResult, getClassification(methodAResult), methodBResult, getClassification(methodBResult), profileMarkClassification, finalClassification
        );
    }

    // Helper method to determine classification based on mark
    private String getClassification(double mark) {
        if (mark >= 70) {
            return "1";
        } else if (mark >= 60) {
            return "2.1";
        } else if (mark >= 50) {
            return "2.2";
        } else if (mark >= 40) {
            return "3rd";
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
            if (getClassification(l5Marks.get(i)).equals("1")) {
                higherClassCredits += credits;
            }
            totalCredits += credits;
        }

        // Calculate Level 6 credit classifications with double weighting
        for (int i = 0; i < l6Marks.size(); i++) {
            int credits = l6Credits.get(i) * 2;  // Double weighting for Level 6
            if (getClassification(l6Marks.get(i)).equals("1")) {
                higherClassCredits += credits;
            }
            totalCredits += credits;
        }

        // If 50% or more of credits are in a higher classification, return First Class
        if ((double) higherClassCredits / totalCredits >= 0.5) {
            return "1";
        } else {
            return "2.1";
        }
    }

    // Helper method to convert classification to numeric value for sorting
    private int getNumericValueFromClassification(String classification) {
        switch (classification) {
            case "1": return 1;  // Best grade
            case "2.1": return 2;
            case "2.2": return 3;
            case "3rd": return 4;
            case "Fail": return 5;  // Worst grade
            default: return Integer.MAX_VALUE;  // Error case
        }
    }

    // Helper method to convert numeric value back to classification string
    private String getClassificationFromNumericValue(int numericValue) {
        switch (numericValue) {
            case 1: return "1";
            case 2: return "2.1";
            case 3: return "2.2";
            case 4: return "3rd";
            case 5: return "Fail";
            default: return "Unknown";
        }
    }
}