import java.util.Scanner;

public class IfThenElseWithLogicalOperators {

    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for pressure and temperature input
        System.out.print("Enter the pressure value: ");
        int pressure = scanner.nextInt();

        System.out.print("Enter the temperature value: ");
        int temperature = scanner.nextInt();

        // Check if pressure is greater than or equal to 150
        // and temperature is greater than or equal to 700
        if (pressure >= 150 && temperature >= 700) {
            System.out.println("High risk");
        } else {
            System.out.println("Normal");
        }

        // Close the scanner
        scanner.close();
    }

}
