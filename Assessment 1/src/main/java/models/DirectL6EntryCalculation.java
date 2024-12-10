package models;

import java.util.ArrayList;

public class DirectL6EntryCalculation {
    // Calculate the weighted average mark
    public double calculateWeightedAverage(ArrayList<Integer> credits, ArrayList<Double> marks) {
        double totalMarks = 0;
        int totalCredits = 0;

        for (int i = 0; i < credits.size(); i++) {
            totalMarks += marks.get(i) * credits.get(i);
            totalCredits += credits.get(i);
        }

        return totalCredits > 0 ? totalMarks / totalCredits : 0;
    }

    // Determine the initial classification based on the average mark
    public String getClassification(double averageMark) {
        if (averageMark >= 70) {
            return "First Class";
        } else if (averageMark >= 60) {
            return "Upper Second Class (2:1)";
        } else if (averageMark >= 50) {
            return "Lower Second Class (2:2)";
        } else {
            return "Fail";
        }
    }

    // Perform mark profiling to adjust the classification
    public String markProfiling(ArrayList<Integer> credits, ArrayList<Double> marks, String initialClassification) {
        int higherClassificationCredits = 0;
        int totalCredits = 0;

        for (int i = 0; i < marks.size(); i++) {
            totalCredits += credits.get(i);
            if (marks.get(i) >= 60) {
                higherClassificationCredits += credits.get(i);
            }
        }

        if ((double) higherClassificationCredits / totalCredits > 0.5) {
            if (initialClassification.equals("Upper Second Class (2:1)")) {
                return "First Class";
            } else if (initialClassification.equals("Lower Second Class (2:2)")) {
                return "Upper Second Class (2:1)";
            }
        }

        return initialClassification;
    }
}