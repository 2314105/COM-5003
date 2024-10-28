import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a SimpleBanking object with initial account details
        SimpleBanking account = new SimpleBanking("John Doe", "123456789", 1000.00);
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            // Display menu
            System.out.println("\nWelcome, " + account.getAccountName() + "\n"
                    + "\t1. View account information \n"
                    + "\t2. Deposit \n"
                    + "\t3. Withdraw \n"
                    + "\t4. Exit \n"
                    + "Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> account.viewInformation();
                case 2 -> account.deposit();
                case 3 -> account.withdraw();
                case 4 -> account.exit();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close(); // Close the scanner to avoid resource leak
    }
}