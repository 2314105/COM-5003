import java.util.Scanner;

// Data class to encapsulate bank account details
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

    // Method to view account information
    public void viewInformation() {
        System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
        System.out.println("-".repeat(40));
        System.out.printf("%-20s %-10s $%.2f %n", accountName, accountNumber, accountBalance);
    }

    // Method to deposit an amount
    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Successfully deposited $" + amount);
            System.out.println("Updated Balance: $" + accountBalance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw an amount
    public void withdraw(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            System.out.println("Updated Balance: $" + accountBalance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a BankAccount object with initial account details
        BankAccount account = new BankAccount("John Doe", "123456789", 1000.00);
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
                case 2 -> {
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                }
                case 3 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                }
                case 4 -> System.out.println("Thank you for using our service!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close(); // Close the scanner
    }
}