import java.util.Scanner;

public class IfThenElseWithLogicalOperators {
    public static void main(String[] args) {
        /*
        Prompt the user to enter pressure and temperature values,
        check if the pressure is >= 150 and temperature is >= 700,
        and print "High risk" or "Normal" based on the evaluation.
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the pressure value: ");
        int pressure = scanner.nextInt();
        System.out.print("Enter the temperature value: ");
        int temperature = scanner.nextInt();
        if (pressure >= 150 && temperature >= 700) {
            System.out.println("High risk");
        } else {
            System.out.println("Normal");
        }
        scanner.close();
    }
}