package model;

public class Module {
    private String code; // Module code, e.g., "COM5023"
    private int credits; // Credits for the module
    private double marks; // Marks for the module

    public Module(String code, int credits, double marks) {
        this.code = code;
        this.credits = credits;
        this.marks = marks;
    }

    public String getCode() {
        return code;
    }

    public int getCredits() {
        return credits;
    }

    public double getMarks() {
        return marks;
    }
}
