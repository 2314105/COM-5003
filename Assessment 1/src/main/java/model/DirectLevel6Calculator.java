package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DirectLevel6Calculator {

    // Classification thresholds
    private static final double FIRST_CLASS_THRESHOLD = 69.50;
    private static final double UPPER_SECOND_THRESHOLD = 59.50;
    private static final double LOWER_SECOND_THRESHOLD = 49.50;
    private static final double THIRD_CLASS_THRESHOLD = 39.50;

    private final AverageCalculator averageCalculator;
    private final MarkClassifier markClassifier;

    // Constructor to inject dependencies
    public DirectLevel6Calculator(AverageCalculator averageCalculator, MarkClassifier markClassifier) {
        this.averageCalculator = averageCalculator;
        this.markClassifier = markClassifier;
    }

    // Method to calculate weighted average for Level 6
    public double calculateL6Average(List<Module> modules) {
        return averageCalculator.calculateAverage(modules);
    }

    // Method to calculate profile classification based on credit distribution
    public String calculateProfileClassification(List<Module> modules) {
        int totalCredits = modules.stream().mapToInt(Module::getCredits).sum();

        // Calculate credits in each classification range
        int creditsFirstClass = getCreditsByClassification(modules, FIRST_CLASS_THRESHOLD, Double.MAX_VALUE);
        int creditsUpperSecond = getCreditsByClassification(modules, UPPER_SECOND_THRESHOLD, FIRST_CLASS_THRESHOLD);
        int creditsLowerSecond = getCreditsByClassification(modules, LOWER_SECOND_THRESHOLD, UPPER_SECOND_THRESHOLD);
        int creditsThirdClass = getCreditsByClassification(modules, THIRD_CLASS_THRESHOLD, LOWER_SECOND_THRESHOLD);

        // Determine profile classification based on credits
        if (creditsFirstClass >= totalCredits / 2) {
            return markClassifier.getClassification(70.0);  // First Class
        }
        if (creditsUpperSecond >= totalCredits / 2) {
            return markClassifier.getClassification(60.0);  // Upper Second Class
        }
        if (creditsLowerSecond >= totalCredits / 2) {
            return markClassifier.getClassification(50.0);  // Lower Second Class
        }
        if (creditsThirdClass >= totalCredits / 2) {
            return markClassifier.getClassification(40.0);  // Third Class
        }

        return markClassifier.getClassification(0.0);  // Fail
    }

    // Method to calculate final classification for the student
    public String calculateFinalClassification(List<Module> modules) {
        double average = calculateL6Average(modules);
        String profileClassification = calculateProfileClassification(modules);

        // Convert average and profile classifications to numeric values
        int averageNumericValue = markClassifier.getNumericValueFromClassification(markClassifier.getClassification(average));
        int profileNumericValue = markClassifier.getNumericValueFromClassification(profileClassification);

        // Debugging output to check values
        System.out.println("Average Numeric Value: " + averageNumericValue);
        System.out.println("Profile Numeric Value: " + profileNumericValue);

        // Store the numeric values in a list for sorting
        List<Integer> numericResults = Arrays.asList(averageNumericValue, profileNumericValue);

        // Sort the numeric values to pick the best classification (smallest number is best)
        Collections.sort(numericResults);

        // The best classification will be the one corresponding to the smallest number
        int bestNumericResult = numericResults.get(0);

        // Convert the best numeric result back to the classification string
        String finalClassification = markClassifier.getClassificationFromNumericValue(bestNumericResult);

        // Return formatted result
        return formatResults(average, finalClassification, profileClassification);
    }


    // Helper method to calculate credits by classification range
    private int getCreditsByClassification(List<Module> modules, double lowerBound, double upperBound) {
        return modules.stream()
                .filter(module -> module.getMarks() >= lowerBound && module.getMarks() < upperBound)
                .mapToInt(Module::getCredits)
                .sum();
    }

    // Method to format the results
    private String formatResults(double average, String finalClassification, String profileClassification) {
        String averageClassification = markClassifier.getClassification(average); // Recalculate based on average
        StringBuilder result = new StringBuilder();
        result.append("Method C - Average 1 L6: ").append(String.format("%.2f", average))
                .append(" (").append(averageClassification).append(")\n"); // Use correct classification here
        result.append("Method D - Profile Mark Classification: ").append(profileClassification).append("\n");
        result.append("Resulting Classification: ").append(finalClassification).append("\n");
        return result.toString();
    }

}
