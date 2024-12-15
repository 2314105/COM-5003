package model;

import java.util.List;

public class DirectLevel6Calculator {

    // Classification thresholds
    private static final double FIRST_CLASS_THRESHOLD = 69.50;
    private static final double UPPER_SECOND_THRESHOLD = 59.50;
    private static final double LOWER_SECOND_THRESHOLD = 49.50;
    private static final double THIRD_CLASS_THRESHOLD = 39.50;

    // Calculate weighted average for Level 6
    public static double calculateL6Average(List<Module> modules) {
        double weightedSum = 0;
        int totalCredits = 0;

        for (Module module : modules) {
            int credits = module.getCredits();
            double marks = module.getMarks();
            weightedSum += credits * marks;
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            return 0;
        }

        double average = weightedSum / totalCredits;
        return Math.round(average * 100.0) / 100.0; // Rounded to two decimal places
    }

    // Calculate profile classification based on credit distribution
    public static String calculateProfileClassification(List<Module> modules) {
        int totalCredits = modules.stream().mapToInt(Module::getCredits).sum();

        int creditsFirstClass = getCreditsByClassification(modules, FIRST_CLASS_THRESHOLD, Double.MAX_VALUE);
        int creditsUpperSecond = getCreditsByClassification(modules, UPPER_SECOND_THRESHOLD, FIRST_CLASS_THRESHOLD);
        int creditsLowerSecond = getCreditsByClassification(modules, LOWER_SECOND_THRESHOLD, UPPER_SECOND_THRESHOLD);
        int creditsThirdClass = getCreditsByClassification(modules, THIRD_CLASS_THRESHOLD, LOWER_SECOND_THRESHOLD);

        if (creditsFirstClass >= totalCredits / 2) return "1st";
        if (creditsUpperSecond >= totalCredits / 2) return "2:1";
        if (creditsLowerSecond >= totalCredits / 2) return "2:2";
        if (creditsThirdClass >= totalCredits / 2) return "3rd";

        return "Fail";
    }

    // Calculate final classification for the student
    public static String calculateFinalClassification(List<Module> modules) {
        double average = calculateL6Average(modules);
        String profileClassification = calculateProfileClassification(modules);

        String finalClassification;
        if (average >= FIRST_CLASS_THRESHOLD) {
            finalClassification = "1st";
        } else if (average >= UPPER_SECOND_THRESHOLD) {
            finalClassification = "2:1";
        } else if (average >= LOWER_SECOND_THRESHOLD) {
            finalClassification = "2:2";
        } else if (average >= THIRD_CLASS_THRESHOLD) {
            finalClassification = "3rd";
        } else {
            finalClassification = "Fail";
        }

        return String.format(
                "Average: %.2f\nClassification: %s\nProfile Classification: %s",
                average, finalClassification, profileClassification
        );
    }

    // Helper method to calculate credits by classification range
    private static int getCreditsByClassification(List<Module> modules, double lowerBound, double upperBound) {
        return modules.stream()
                .filter(module -> module.getMarks() >= lowerBound && module.getMarks() < upperBound)
                .mapToInt(Module::getCredits)
                .sum();
    }
}