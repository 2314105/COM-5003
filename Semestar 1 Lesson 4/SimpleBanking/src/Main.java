import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize account details
        String accountName = "John Doe";
        String accountNumber = "123456789";
        double accountBalance = 1000.00;
        int choice;
        Scanner sc = new Scanner(System.in);

        // Menu loop
        do {
            // Display menu options
            System.out.println("\nWelcome, " + accountName + "\n"
                    + "\t1. View account information \n"
                    + "\t2. Deposit \n"
                    + "\t3. Withdraw \n"
                    + "\t4. Exit \n"
                    + "Enter your choice: ");
            choice = sc.nextInt();

            // Handle the user's choice
            switch (choice) {
                case 1 -> {
                    // View account details
                    System.out.printf("Account: %s, Number: %s, Balance: $%.2f\n", accountName, accountNumber, accountBalance);
                }
                case 2 -> {
                    // Deposit money into the account
                    System.out.print("Enter deposit amount: ");
                    int amount = sc.nextInt();
                    if (amount > 0) {
                        accountBalance += amount; // Update balance
                        System.out.println("Successfully deposited $" + amount);
                    } else {
                        System.out.println("Invalid deposit amount.");
                    }
                }
                case 3 -> {
                    // Withdraw money from the account
                    System.out.print("Enter withdrawal amount: ");
                    int amount = sc.nextInt();
                    if (amount > 0 && amount <= accountBalance) {
                        accountBalance -= amount; // Update balance
                        System.out.println("Successfully withdrew $" + amount);
                    } else {
                        System.out.println("Invalid withdrawal amount or insufficient funds.");
                    }
                }
                case 4 -> System.out.println("Thank you for using our service!"); // Exit message
                default -> System.out.println("Invalid choice. Please try again."); // Invalid input handling
            }
        } while (choice != 4); // Continue looping until the user chooses to exit
    }
}