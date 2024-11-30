import java.util.Scanner;

public class SwitchStatement {
    public static void main(String[] args) {
        /*
        Prompt the user to enter a number between 1 and 3,
        use a switch statement to determine and print the corresponding day of the week,
        or print "Invalid input" if the number is outside the range.
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number (1 - 3): ");
        int day = scanner.nextInt();
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
        scanner.close();
    }
}