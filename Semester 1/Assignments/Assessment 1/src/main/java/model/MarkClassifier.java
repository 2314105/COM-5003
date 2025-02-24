package model;

/*
This class provides methods for classifying marks and converting between
classification strings and numeric values. It supports operations such as:
- Classifying marks based on grade thresholds.
- Converting classifications to numeric values for sorting purposes.
- Converting numeric values back to classification strings.
*/
public class MarkClassifier {

    /*
    Classifies a given mark into a degree classification based on predefined thresholds.
    Returns one of the following classifications:
    - "1" for First Class
    - "2.1" for Upper Second Class
    - "2.2" for Lower Second Class
    - "3rd" for Third Class
    - "Fail" for marks below 40.
    */
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

    /*
    Converts a classification string into a numeric value for comparison and sorting.
    Returns:
    - 1 for "1" (First Class)
    - 2 for "2.1" (Upper Second Class)
    - 3 for "2.2" (Lower Second Class)
    - 4 for "3rd" (Third Class)
    - 5 for "Fail"
    Returns Integer.MAX_VALUE for unknown classifications.
    */
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

    /*
    Converts a numeric value back into a classification string.
    Returns:
    - "1" for 1 (First Class)
    - "2.1" for 2 (Upper Second Class)
    - "2.2" for 3 (Lower Second Class)
    - "3rd" for 4 (Third Class)
    - "Fail" for 5
    Returns "Unknown" for values outside the valid range.
    */
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
