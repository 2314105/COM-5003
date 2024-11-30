import java.util.Scanner;

public class IfThenElseWithMultipleConditions {
    public static void main(String[] args) {
        /*
        Prompt the user to enter an integer,
        check if it is between 40 and 80 inclusive,
        and print "Safe" or "Overspeed" based on the evaluation.
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int y = scanner.nextInt();
        if (y >= 40 && y <= 80) {
            System.out.println("Safe");
        } else {
            System.out.println("Overspeed");
        }
        scanner.close();
    }
}