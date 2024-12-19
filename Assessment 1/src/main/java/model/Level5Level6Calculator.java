package model;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/*
This class calculates degree classifications based on marks and credits for Level 5 and Level 6 modules.
It supports multiple calculation methods (A, B, and D) to determine the final classification.
*/
public class Level5Level6Calculator {

    private MarkClassifier markClassifier = new MarkClassifier();
    private AverageCalculator averageCalculator = new AverageCalculator();

    /*
    Method A: Calculates the overall average of Level 5 and Level 6 module marks,
    with equal weighting for both levels.
    */
    public double calculateMethodA(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double l5Average = averageCalculator.calculateAverage(createModules(l5Marks, l5Credits, "Level 5"));
        double l6Average = averageCalculator.calculateAverage(createModules(l6Marks, l6Credits, "Level 6"));

        // Calculate and round the final result
        double result = (l5Average + l6Average) / 2;
        return Math.round(result * 100.0) / 100.0;
    }

    /*
    Method B: Calculates the overall average with Level 6 marks weighted twice as much as Level 5.
    */
    public double calculateMethodB(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double l5Average = averageCalculator.calculateAverage(createModules(l5Marks, l5Credits, "Level 5"));
        double l6Average = averageCalculator.calculateAverage(createModules(l6Marks, l6Credits, "Level 6"));

        // Calculate the weighted average
        return (l5Average + 2 * l6Average) / 3;
    }

    /*
    Method D: Determines the best classification among Method A, Method B, and Profile Mark Classification.
    */
    public String calculateMethodD(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        double methodAResult = calculateMethodA(l5Marks, l5Credits, l6Marks, l6Credits);
        double methodBResult = calculateMethodB(l5Marks, l5Credits, l6Marks, l6Credits);

        // Calculate the profile mark classification
        String profileMarkClassification = getProfileMarkClassification(l5Marks, l5Credits, l6Marks, l6Credits);

        // Convert results to numeric values for comparison
        int methodANumericValue = markClassifier.getNumericValueFromClassification(markClassifier.getClassification(methodAResult));
        int methodBNumericValue = markClassifier.getNumericValueFromClassification(markClassifier.getClassification(methodBResult));
        int profileNumericValue = markClassifier.getNumericValueFromClassification(profileMarkClassification);

        // Determine the best classification
        List<Integer> numericResults = Arrays.asList(methodANumericValue, methodBNumericValue, profileNumericValue);
        Collections.sort(numericResults);
        int bestNumericResult = numericResults.get(0);
        String finalClassification = markClassifier.getClassificationFromNumericValue(bestNumericResult);

        // Format and return the results
        return String.format(
                "Method A - Average 1 (Level 5 + Level 6): %.2f (%s)\nMethod B - Average 2 (Level 5 + Level 6 x2): %.2f (%s)\nMethod D - Profile Mark Classification: %s\nResulting Classification: %s",
                methodAResult, markClassifier.getClassification(methodAResult), methodBResult, markClassifier.getClassification(methodBResult), profileMarkClassification, finalClassification
        );
    }

    /*
    Helper method to create a list of modules from marks and credits for a specific level.
    */
    private List<Module> createModules(List<Double> marks, List<Integer> credits, String level) {
        List<Module> modules = new ArrayList<>();
        for (int i = 0; i < marks.size(); i++) {
            modules.add(new Module(level, credits.get(i), marks.get(i)));
        }
        return modules;
    }

    /*
    Helper method to calculate the profile mark classification based on the total credits
    in higher classifications for Level 5 and Level 6.
    */
    private String getProfileMarkClassification(List<Double> l5Marks, List<Integer> l5Credits, List<Double> l6Marks, List<Integer> l6Credits) {
        int totalCredits = 0;
        int higherClassCredits = 0;

        // Calculate higher classification credits for Level 5
        for (int i = 0; i < l5Marks.size(); i++) {
            int credits = l5Credits.get(i);
            if (markClassifier.getClassification(l5Marks.get(i)).equals("1")) {
                higherClassCredits += credits;
            }
            totalCredits += credits;
        }

        // Calculate higher classification credits for Level 6 (double weighting)
        for (int i = 0; i < l6Marks.size(); i++) {
            int credits = l6Credits.get(i) * 2;
            if (markClassifier.getClassification(l6Marks.get(i)).equals("1")) {
                higherClassCredits += credits;
            }
            totalCredits += credits;
        }

        // Determine classification based on credit distribution
        if ((double) higherClassCredits / totalCredits >= 0.5) {
            return "1";
        } else {
            return "2.1";
        }
    }
}
