package model;

public class MarkClassifier {

    // Method to classify the mark based on the grade
    public String getClassification(double mark) {
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

    // Method to convert classification to numeric value for sorting
    public int getNumericValueFromClassification(String classification) {
        switch (classification) {
            case "1": return 1;  // Best grade
            case "2.1": return 2;
            case "2.2": return 3;
            case "3rd": return 4;
            case "Fail": return 5;  // Worst grade
            default: return Integer.MAX_VALUE;  // Error case
        }
    }

    // Method to convert numeric value back to classification string
    public String getClassificationFromNumericValue(int numericValue) {
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
