import java.util.Scanner;

public class IfThenElse {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter an integer
        System.out.print("Enter an integer: ");
        int y = scanner.nextInt();

        // Check if y is between 10 and 30 inclusive
        if (y >= 10 && y <= 30) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}