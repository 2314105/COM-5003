package model;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Level5Level6Calculator {

    private MarkClassifier markClassifier = new MarkClassifier();
    private AverageCalculator averageCalculator = new AverageCalculator();

    public double calculateMethodA(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double l5Average = averageCalculator.calculateAverage(createModules(l5Marks, l5Credits, "Level 5"));
        double l6Average = averageCalculator.calculateAverage(createModules(l6Marks, l6Credits, "Level 6"));

        // Explicit rounding after combining averages
        double result = (l5Average + l6Average) / 2;
        return Math.round(result * 100.0) / 100.0;
    }



    // Method B: The average mark of all module marks achieved at Level 5 and Level 6, weighted 2:1 for Level 6
    public double calculateMethodB(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double l5Average = averageCalculator.calculateAverage(createModules(l5Marks, l5Credits, "Level 5"));
        double l6Average = averageCalculator.calculateAverage(createModules(l6Marks, l6Credits, "Level 6"));

        // Calculate overall average (Method B), with Level 6 weighted 2:1
        return (l5Average + 2 * l6Average) / 3;
    }

    // Method D: Mark profiling methods A and B
    public String calculateMethodD(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double methodAResult = calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);

        // Profile the marks by checking classifications based on total credits
        String profileMarkClassification = getProfileMarkClassification(l5Marks, l5Credits, l6Marks, l6Credits);

        // Convert Method A, Method B, and Profile Mark classifications to numeric values
        int methodANumericValue = markClassifier.getNumericValueFromClassification(markClassifier.getClassification(methodAResult));
        int methodBNumericValue = markClassifier.getNumericValueFromClassification(markClassifier.getClassification(methodBResult));
        int profileNumericValue = markClassifier.getNumericValueFromClassification(profileMarkClassification);

        // Store the numeric values in a list for sorting
        List<Integer> numericResults = Arrays.asList(methodANumericValue, methodBNumericValue, profileNumericValue);

        // Sort the numeric values to pick the best classification (smallest number is best)
        Collections.sort(numericResults);

        // The best classification will be the one corresponding to the smallest number
        int bestNumericResult = numericResults.get(0);

        // Convert the best numeric result back to the classification string
        String finalClassification = markClassifier.getClassificationFromNumericValue(bestNumericResult);

        // Format the results
        return String.format(
                "Method A - Average 1 (Level 5 + Level 6): %.2f (%s)\nMethod B - Average 2 (Level 5 + Level 6 x2): %.2f (%s)\nMethod D - Profile Mark Classification: %s\nResulting Classification: %s",
                methodAResult, markClassifier.getClassification(methodAResult), methodBResult, markClassifier.getClassification(methodBResult), profileMarkClassification, finalClassification
        );
    }

    // Helper method to create modules from marks and credits for both Level 5 and Level 6
    private List<Module> createModules(List<Double> marks, List<Integer> credits, String level) {
        List<Module> modules = new ArrayList<>();
        for (int i = 0; i < marks.size(); i++) {
            modules.add(new Module(level, credits.get(i), marks.get(i)));
        }
        return modules;
    }

    // Helper method to calculate profile mark classification based on the total credits in each classification
    private String getProfileMarkClassification(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        int totalCredits = 0;
        int higherClassCredits = 0;

        // Calculate Level 5 credit classifications
        for (int i = 0; i < l5Marks.size(); i++) {
            int credits = l5Credits.get(i);
            if (markClassifier.getClassification(l5Marks.get(i)).equals("1")) {
                higherClassCredits += credits;
            }
            totalCredits += credits;
        }

        // Calculate Level 6 credit classifications with double weighting
        for (int i = 0; i < l6Marks.size(); i++) {
            int credits = l6Credits.get(i) * 2;  // Double weighting for Level 6
            if (markClassifier.getClassification(l6Marks.get(i)).equals("1")) {
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
}
