package data;

public class Module {
    private String code;
    private int credits;

    // Constructor to initialize the module
    public Module(String code, int credits) {
        this.code = code;
        this.credits = credits;
    }

    // Getter for the code
    public String getCode() {
        return code;
    }

    // Setter for the code
    public void setCode(String code) {
        this.code = code;
    }

    // Getter for credits
    public int getCredits() {
        return credits;
    }

    // Setter for credits
    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return code + " (" + credits + " credits)";
    }
}