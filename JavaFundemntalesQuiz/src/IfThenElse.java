import java.util.Scanner;

public class IfThenElse {
    public static void main(String[] args) {
        /*
        Prompt the user to enter an integer,
        check if it is between 10 and 30 inclusive,
        and print "Valid" or "Invalid" based on the result.
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int y = scanner.nextInt();
        if (y >= 10 && y <= 30) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
        scanner.close();
    }
}