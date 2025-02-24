package model;

/*
This class represents a module in a degree program.
Each module has the following properties:
- A unique module code (e.g., "COM5023").
- A number of credits associated with the module.
- The marks obtained for the module.
*/
public class Module {
    private String code; // Module code, e.g., "COM5023"
    private int credits; // Credits for the module
    private double marks; // Marks for the module

    /*
    Constructor to initialize a module with its code, credits, and marks.
    */
    public Module(String code, int credits, double marks) {
        this.code = code;
        this.credits = credits;
        this.marks = marks;
    }

    /*
    Returns the module code.
    */
    public String getCode() {
        return code;
    }

    /*
    Returns the number of credits for the module.
    */
    public int getCredits() {
        return credits;
    }

    /*
    Returns the marks obtained for the module.
    */
    public double getMarks() {
        return marks;
    }
}
