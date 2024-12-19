package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
This class calculates the degree classification for Level 6 modules.
It uses weighted averages and credit distributions to determine the final classification.
*/
public class DirectLevel6Calculator {

    // Classification thresholds
    private static final double FIRST_CLASS_THRESHOLD = 69.50;
    private static final double UPPER_SECOND_THRESHOLD = 59.50;
    private static final double LOWER_SECOND_THRESHOLD = 49.50;
    private static final double THIRD_CLASS_THRESHOLD = 39.50;

    private final AverageCalculator averageCalculator;
    private final MarkClassifier markClassifier;

    /*
    Constructor to initialize the calculator with dependencies for average calculation
    and mark classification.
    */
    public DirectLevel6Calculator(AverageCalculator averageCalculator, MarkClassifier markClassifier) {
        this.averageCalculator = averageCalculator;
        this.markClassifier = markClassifier;
    }

    /*
    Calculates the weighted average for Level 6 modules using the AverageCalculator.
    */
    public double calculateL6Average(List<Module> modules) {
        return averageCalculator.calculateAverage(modules);
    }

    /*
    Determines the profile classification based on the credit distribution across
    different grade boundaries.
    */
    public String calculateProfileClassification(List<Module> modules) {
        int totalCredits = modules.stream().mapToInt(Module::getCredits).sum();

        // Calculate credits within each classification range
        int creditsFirstClass = getCreditsByClassification(modules, FIRST_CLASS_THRESHOLD, Double.MAX_VALUE);
        int creditsUpperSecond = getCreditsByClassification(modules, UPPER_SECOND_THRESHOLD, FIRST_CLASS_THRESHOLD);
        int creditsLowerSecond = getCreditsByClassification(modules, LOWER_SECOND_THRESHOLD, UPPER_SECOND_THRESHOLD);
        int creditsThirdClass = getCreditsByClassification(modules, THIRD_CLASS_THRESHOLD, LOWER_SECOND_THRESHOLD);

        // Determine classification based on majority of credits
        if (creditsFirstClass >= totalCredits / 2) {
            return markClassifier.getClassification(70.0);
        }
        if (creditsUpperSecond >= totalCredits / 2) {
            return markClassifier.getClassification(60.0);
        }
        if (creditsLowerSecond >= totalCredits / 2) {
            return markClassifier.getClassification(50.0);
        }
        if (creditsThirdClass >= totalCredits / 2) {
            return markClassifier.getClassification(40.0);
        }

        return markClassifier.getClassification(0.0); // Fail
    }

    /*
    Calculates the final classification based on both the weighted average and
    profile classification. The best result is selected as the final classification.
    */
    public String calculateFinalClassification(List<Module> modules) {
        double average = calculateL6Average(modules);
        String profileClassification = calculateProfileClassification(modules);

        // Convert classifications to numeric values for comparison
        int averageNumericValue = markClassifier.getNumericValueFromClassification(markClassifier.getClassification(average));
        int profileNumericValue = markClassifier.getNumericValueFromClassification(profileClassification);

        // Determine the best classification (smallest numeric value)
        List<Integer> numericResults = Arrays.asList(averageNumericValue, profileNumericValue);
        Collections.sort(numericResults);
        int bestNumericResult = numericResults.get(0);

        // Convert the best numeric result back to a classification
        String finalClassification = markClassifier.getClassificationFromNumericValue(bestNumericResult);

        // Format and return the final results
        return formatResults(average, finalClassification, profileClassification);
    }

    /*
    Helper method to calculate the total credits within a specific classification range.
    */
    private int getCreditsByClassification(List<Module> modules, double lowerBound, double upperBound) {
        return modules.stream()
                .filter(module -> module.getMarks() >= lowerBound && module.getMarks() < upperBound)
                .mapToInt(Module::getCredits)
                .sum();
    }

    /*
    Formats the results for display, including the average classification and
    profile classification.
    */
    private String formatResults(double average, String finalClassification, String profileClassification) {
        String averageClassification = markClassifier.getClassification(average); // Classification based on average
        StringBuilder result = new StringBuilder();
        result.append("Method C - Average 1 L6: ").append(String.format("%.2f", average))
                .append(" (").append(averageClassification).append(")\n");
        result.append("Method D - Profile Mark Classification: ").append(profileClassification).append("\n");
        result.append("Resulting Classification: ").append(finalClassification).append("\n");
        return result.toString();
    }
}
