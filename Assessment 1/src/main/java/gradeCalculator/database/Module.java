package gradeCalculator.database;

public class Module {
    private String moduleName;
    private int credits;

    public Module(String moduleName, int credits) {
        this.moduleName = moduleName;
        this.credits = credits;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return moduleName + " (" + credits + " credits)";
    }
}