
import java.util.Scanner;

public class SwitchStatement {

    public static void main(String[] args) {
        // Create a scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Ask for user input
        System.out.print("Enter a number ( 1 - 3 ): ");
        int day = scanner.nextInt();

        // Use a switch statement to determine the day of the week
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Invalid input");
        }

        // Close the scanner
        scanner.close();
    }
}


