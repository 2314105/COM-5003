package gradeCalculator.calculations;

import java.util.List;

public class MethodD {
    public static String calculateMarkProfile(List<Integer> credits, List<Integer> classifications) {
        int totalCredits = 0;
        int higherClassCredits = 0;

        for (int i = 0; i < credits.size(); i++) {
            totalCredits += credits.get(i);
            if (classifications.get(i) == 1) { // Assuming 1 is a higher classification
                higherClassCredits += credits.get(i);
            }
        }

        if (higherClassCredits >= totalCredits / 2) {
            return "1st";
        }
        return "Lower classification";
    }
}