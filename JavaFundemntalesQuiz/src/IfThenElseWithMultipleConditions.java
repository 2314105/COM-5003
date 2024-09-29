import java.util.Scanner;

public class IfThenElseWithMultipleConditions {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter an integer
        System.out.print("Enter an integer: ");
        int y = scanner.nextInt();

        // Check if y is between 10 and 30 inclusive
        if (y >= 40 && y <= 80) {
            System.out.println("Safe");
        } else {
            System.out.println("Overspeed");
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}