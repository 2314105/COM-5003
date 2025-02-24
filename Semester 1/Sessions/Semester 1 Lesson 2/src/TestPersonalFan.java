import java.util.Scanner;

public class TestPersonalFan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonalFan myFan = new PersonalFan(); // Create a Fan object
        String border = "<------------------------------------------------>";
        char symbol = '>';
        int userOption;
        String fanOptions = "\t0 - OFF\n\t1 - Speed 1\n\t2 - Speed 2\n\t3 - Speed 3\n\t4 - Speed 4\n\t5 - Speed 5";

        // Main loop to control fan settings
        while (true) {
            System.out.println(border);
            System.out.println(fanOptions);
            System.out.println(border);
            System.out.print(symbol);
            userOption = scanner.nextInt();

            switch (userOption) {
                case 0:
                    myFan.turnOff();
                    System.out.println("Fan Off");
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    myFan.setSpeed(userOption);
                    System.out.println("Fan Speed = " + myFan.getSpeed());
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

            // Print the current fan state
            if (myFan.isFanOn()) {
                System.out.println("The fan is currently ON.");
            } else {
                System.out.println("The fan is currently OFF.");
            }

            // Exit the loop if the fan is turned off
            if (userOption == 0) {
                break;
            }
        }

        // Close the scanner
        scanner.close();
    }
}