import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a BankAccount object with initial values
        BankAccount account = new BankAccount("John Doe", "123456789", 1000.00);
        int choice;
        Scanner sc = new Scanner(System.in);

        // Menu loop
        do {
            // Display menu options
            System.out.println("\nWelcome, " + account.getAccountName() + "\n"
                    + "\t1. View account information \n"
                    + "\t2. Deposit \n"
                    + "\t3. Withdraw \n"
                    + "\t4. Exit \n"
                    + "Enter your choice: ");
            choice = sc.nextInt();

            // Perform actions based on the user's choice
            switch (choice) {
                case 1 -> account.viewInformation(); // View account information
                case 2 -> account.deposit(sc); // Deposit amount
                case 3 -> account.withdraw(sc); // Withdraw amount
                case 4 -> account.exit(); // Exit the program
                default -> System.out.println("Invalid choice. Please try again."); // Handle invalid input
            }
        } while (choice != 4); // Repeat until user selects 'Exit'
    }
}

class BankAccount {
    private String accountName;
    private String accountNumber;
    private double accountBalance;

    // Constructor to initialize account details
    public BankAccount(String name, String number, double balance) {
        this.accountName = name;
        this.accountNumber = number;
        this.accountBalance = balance;
    }

    // Getter for account name
    public String getAccountName() {
        return accountName;
    }

    // Method to deposit money into the account
    public void deposit(Scanner sc) {
        System.out.print("Enter deposit amount: ");
        int amount = sc.nextInt();
        if (amount > 0) {
            accountBalance += amount; // Update balance with deposit amount
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount."); // Handle invalid deposit
        }
    }

    // Method to withdraw money from the account
    public void withdraw(Scanner sc) {
        System.out.print("Enter withdrawal amount: ");
        int amount = sc.nextInt();
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount; // Update balance with withdrawal amount
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds."); // Handle invalid withdrawal
        }
    }

    // Method to print a thank you message when the user exits
    public void exit() {
        System.out.println("Thank you for using our service!"); // Exit message
    }

    // Method to display account information
    public void viewInformation() {
        System.out.printf("Account: %s, Number: %s, Balance: $%.2f\n", accountName, accountNumber, accountBalance); // Print account details
    }
}