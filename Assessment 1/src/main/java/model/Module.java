package model;

public class Module {
    private String name;
    private int credits;
    private double marks;

    public Module(String name, int credits, double marks) {
        this.name = name;
        this.credits = credits;
        this.marks = marks;
    }

    public int getCredits() {
        return credits;
    }

    public double getMarks() {
        return marks;
    }
}