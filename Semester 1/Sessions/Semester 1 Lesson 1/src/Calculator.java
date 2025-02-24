import java.util.Scanner;

public class Calculator {
    private static String border = "<===========================================================================>";

    public static void main(String[] args) {
        // Create an instance of CCalculator
        CCalculator calculator = new CCalculator();
        // Start the UI
        calculator.UI();
    }

    public static class CCalculator {
        // Fields (attributes)
        private Double mAns;
        private String mOperation;
        private double mNumber;
        private String mEquation;
        private double mResult;

        // Constructor
        public CCalculator() {
            this.mAns = null;
            this.mOperation = "";
            this.mNumber = 0.0;
            this.mEquation = "";
            this.mResult = 0.0;
        }

        // Method to perform addition
        public double Add() {
            mResult = mAns + mNumber; // Use mAns for addition
            mOperation = "Addition"; // Set the operation type
            return mResult; // Return the result
        }

        // Method to perform subtraction
        public double Subtract() {
            mResult = mAns - mNumber;
            mOperation = "Subtraction";
            return mResult;
        }

        // Method to perform multiplication
        public double Multiply() {
            mResult = mAns * mNumber;
            mOperation = "Multiplication";
            return mResult;
        }

        // Method to perform division
        public double Divide() {
            if (mNumber != 0) {
                mResult = mAns / mNumber;
                mOperation = "Division";
            } else {
                System.out.println("Cannot divide by zero.");
            }
            return mResult;
        }

        // Method to perform modulus
        public double Modulus() {
            mResult = mAns % mNumber;
            mOperation = "Modulus";
            return mResult;
        }

        // Method to perform power
        public double Power() {
            mResult = Math.pow(mAns, mNumber);
            mOperation = "Power";
            return mResult;
        }

        // Method to perform square root
        public double SquareRoot() {
            mResult = Math.sqrt(mAns);
            mOperation = "Square Root";
            return mResult;
        }

        public void UI() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    clearScreen();
                    // Displaying the calculator information
                    System.out.println(border);
                    System.out.println("\t\t\t\t\t\tScientific Calculator");
                    System.out.println(border);
                    System.out.println("Ans: " + (mAns != null ? mAns : "No answer"));
                    System.out.println("Last operation: " + mOperation);
                    System.out.println("Equation: " + mEquation);
                    System.out.println("Result: " + mResult);
                    System.out.println(border);

                    if (mAns == null) {
                        System.out.print("Enter a starting number: ");
                        mAns = scanner.nextDouble(); // Set initial answer
                        mResult = mAns; // Initialize result
                        scanner.nextLine(); // Clear newline character
                    }

                    // Display available operations
                    System.out.println("Operations:\n>Add\n>Subtract\n>Multiply\n>Divide\n>Modulus\n>Power\n>Square Root\n>Clear\n>Exit");
                    System.out.print("Enter an operation: ");
                    mOperation = scanner.nextLine();

                    if (mOperation.equalsIgnoreCase("Exit")) {
                        break; // Exit the loop
                    } else if (mOperation.equalsIgnoreCase("Clear")) {
                        mAns = null; // Reset the calculator
                        mResult = 0.0;
                        mOperation = "";
                        mEquation = "";
                        continue; // Restart the loop
                    }

                    System.out.print("Enter a number: ");
                    mNumber = scanner.nextDouble(); // Read the number
                    scanner.nextLine(); // Clear newline character

                    switch (mOperation.toLowerCase()) {
                        case "add":
                        case "+":
                            mResult = Add();
                            mEquation = mAns + " + " + mNumber;
                            break;
                        case "subtract":
                        case "-":
                            mResult = Subtract();
                            mEquation = mAns + " - " + mNumber;
                            break;
                        case "multiply":
                        case "*":
                            mResult = Multiply();
                            mEquation = mAns + " * " + mNumber;
                            break;
                        case "divide":
                        case "/":
                            mResult = Divide();
                            mEquation = mAns + " / " + mNumber;
                            break;
                        case "modulus":
                        case "%":
                            mResult = Modulus();
                            mEquation = mAns + " % " + mNumber;
                            break;
                        case "power":
                            mResult = Power();
                            mEquation = mAns + " ^ " + mNumber;
                            break;
                        case "square root":
                        case "√":
                            mResult = SquareRoot();
                            mEquation = "√" + mAns; // Square root does not use mNumber
                            break;
                        default:
                            System.out.println("Invalid operation. Please try again.");
                    }

                    mAns = mResult; // Update mAns with the latest result

                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                    scanner.nextLine(); // Clear the invalid input
                }
            }
            scanner.close();
        }

        // Clear the screen by printing empty lines
        public static void clearScreen() {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}