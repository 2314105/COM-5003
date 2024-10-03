import java.util.Scanner;

public class PersonalFan {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String border = "<------------------------------------------------>";
        char symbol = '>';
        int userOption = 0;
        boolean isFanOn = false; // Boolean to check if the fan is on or off
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
                    isFanOn = false;
                    System.out.println("Fan Off");
                    break;
                case 1:
                    isFanOn = true;
                    System.out.println("Fan Speed = 1");
                    break;
                case 2:
                    isFanOn = true;
                    System.out.println("Fan Speed = 2");
                    break;
                case 3:
                    isFanOn = true;
                    System.out.println("Fan Speed = 3");
                    break;
                case 4:
                    isFanOn = true;
                    System.out.println("Fan Speed = 4");
                    break;
                case 5:
                    isFanOn = true;
                    System.out.println("Fan Speed = 5");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

            // Print the current fan state
            if (isFanOn) {
                System.out.println("The fan is currently ON.");
            } else {
                System.out.println("The fan has been turned OFF.");
            }

            // Exit the loop if fan is turned off (userOption == 0)
            if (userOption == 0) {
                break;
            }
        }

        // Closing the scanner
        scanner.close();
    }
}