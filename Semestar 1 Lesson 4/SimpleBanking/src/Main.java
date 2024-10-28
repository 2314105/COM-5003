import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an object with initial account details.
        // This allows for easy management of a single bank account.
        SimpleBanking account = new SimpleBanking("John Doe", "123456789", 1000.00);
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            // Display menu for user interaction.
            // This menu presents the user with options to manage the bank account.
            System.out.println("\nWelcome, " + account.getAccountName() + "\n"
                    + "\t1. View account information \n"
                    + "\t2. Deposit \n"
                    + "\t3. Withdraw \n"
                    + "\t4. Exit \n"
                    + "Enter your choice: ");
            choice = sc.nextInt();

            // Switch statement to handle menu selections.
            // Each case corresponds to a different action related to the bank account.
            switch (choice) {
                case 1 -> account.viewInformation(); // Calls method to view account details.
                case 2 -> account.deposit(); // Calls method to deposit funds.
                case 3 -> account.withdraw(); // Calls method to withdraw funds.
                case 4 -> account.exit(); // Calls method to exit the application.
                default -> System.out.println("Invalid choice. Please try again."); // Error message for invalid input.
            }
        } while (choice != 4); // Loop continues until the user chooses to exit.
    }
}

// SimpleBanking class to encapsulate bank account details and operations.
class SimpleBanking {
    // Instance variables for account information.
    private String accountName;
    private String accountNumber;
    private double accountBalance;

    // Scanner object for user input.
    private Scanner sc = new Scanner(System.in);

    // Constructor to initialize account details.
    public SimpleBanking(String name, String number, double balance) {
        this.accountName = name;
        this.accountNumber = number;
        this.accountBalance = balance;
    }

    // Getter method for account name.
    public String getAccountName() {
        return accountName;
    }

    // Method to deposit an amount into the account.
    public void deposit() {
        System.out.print("Enter deposit amount: ");
        int amount = sc.nextInt();
        // Validates that the deposit amount is positive before updating the balance.
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Successfully deposited $" + amount);
            System.out.println("Updated Balance: $" + accountBalance);
        } else {
            System.out.println("Invalid deposit amount."); // Error message for invalid input.
        }
    }

    // Method to withdraw an amount from the account.
    public void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        int amount = sc.nextInt();
        // Validates that the withdrawal amount is positive and does not exceed the account balance.
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            System.out.println("Updated Balance: $" + accountBalance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds."); // Error message for invalid input.
        }
    }

    // Method to exit the application and display a thankyou message.
    public void exit() {
        System.out.println("Thank you for using our service!");
    }

    // Method to display account information in a formatted manner.
    public void viewInformation() {
        // Display account information with formatted output.
        System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
        System.out.println("-".repeat(40)); // Creates a visual separator.
        System.out.printf("%-20s %-10s $%.2f %n", accountName, accountNumber, accountBalance);
    }
}