package model;

import java.util.List;

public class DirectLevel6Calculator {

    // Method C
    public static double calculateL6Average(List<Module> modules) {
        double weightedSum = 0;
        int totalCredits = 0;

        for (Module module : modules) {
            weightedSum += module.getCredits() * module.getMarks(); // Updated to getMarks()
            totalCredits += module.getCredits();
        }

        return totalCredits > 0 ? Math.round((weightedSum / totalCredits) * 10.0) / 10.0 : 0; // Rounded to 1 decimal place
    }

    // Method D
    public static String calculateL6Profiling(List<Module> modules) {
        int credits1st = 0;
        int credits21 = 0;
        int credits22 = 0;
        int credits3rd = 0;
        int creditsFail = 0;
        int totalCredits = 0;

        for (Module module : modules) {
            double mark = module.getMarks(); // Updated to getMarks()
            int credits = module.getCredits();
            totalCredits += credits;

            if (mark >= 69.50) {
                credits1st += credits;
            } else if (mark >= 59.50) {
                credits21 += credits;
            } else if (mark >= 49.50) {
                credits22 += credits;
            } else if (mark >= 39.50) {
                credits3rd += credits;
            } else {
                creditsFail += credits;
            }
        }

        if (credits1st >= totalCredits / 2) return "1st";
        if (credits21 >= totalCredits / 2) return "2:1";
        if (credits22 >= totalCredits / 2) return "2:2";
        if (credits3rd >= totalCredits / 2) return "3rd";

        return "Fail";
    }
}