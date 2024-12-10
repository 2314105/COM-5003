package models;

import java.util.ArrayList;

public class L5AndL6ModulesCalculation {
    public static double aggregateFields(ArrayList<Integer> credits, ArrayList<Double> marks) {
        double totalWeightedMarks = 0;

        for (int i = 0; i < credits.size(); i++) {
            int credit = credits.get(i);
            double mark = marks.get(i);

            if (credit > 0 && mark >= 0 && mark <= 100) {
                totalWeightedMarks += mark * credit;
            }
        }

        return totalWeightedMarks;
    }

    public static String getClassification(double averageMark) {
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

    public static String markProfiling(ArrayList<Integer> credits, ArrayList<Double> marks, String initialClassification) {
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