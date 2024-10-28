import java.util.ArrayList;
import java.util.Scanner;

// The BankAccount class encapsulates all details and actions related to a single bank account.
// Using a separate class for account details promotes code reuse and makes the code modular.
class BankAccount {
    private String accountName;
    private String accountNumber;
    private double accountBalance;

    // Constructor initializes account details upon object creation.
    public BankAccount(String name, String number, double balance) {
        this.accountName = name;
        this.accountNumber = number;
        this.accountBalance = balance;
    }

    // Getters provide controlled access to accountName and accountNumber.
    // No setters are provided as these details shouldn't be modified once the account is created.
    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Displays account information in a formatted way, making it easier to read in the console.
    public void viewInformation() {
        System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
        System.out.println("-".repeat(40));
        System.out.printf("%-20s %-10s $%.2f %n", accountName, accountNumber, accountBalance);
    }

    // Increases the account balance by a specified amount.
    // The method includes input validation to ensure positive deposit amounts.
    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Successfully deposited $" + amount);
            System.out.println("Updated Balance: $" + accountBalance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Decreases the account balance by a specified amount, with input validation.
    // Ensures that the withdrawal doesn't exceed the current balance to prevent overdraft.
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
    // The accounts ArrayList stores multiple BankAccount objects, allowing for management of multiple accounts.
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nBank Account Management System\n"
                    + "\t1. Add new account\n"
                    + "\t2. Select an account\n"
                    + "\t3. Exit\n"
                    + "Enter your choice: ");
            choice = sc.nextInt();

            // Uses a switch statement to direct the program flow based on the user's choice.
            // The main menu offers options to add an account, select an account, or exit.
            switch (choice) {
                case 1 -> addAccount();
                case 2 -> selectAccount();
                case 3 -> System.out.println("Thank you for using our service!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        sc.close(); // Closes the Scanner resource once the program is done.
    }

    // Prompts the user to enter details for a new bank account, creating a new BankAccount object.
    // Adds the newly created BankAccount to the accounts ArrayList.
    private static void addAccount() {
        System.out.print("Enter account holder's name: ");
        String name = sc.next();
        System.out.print("Enter account number: ");
        String number = sc.next();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        BankAccount account = new BankAccount(name, number, balance);
        accounts.add(account);
        System.out.println("Account added successfully!");
    }

    // Allows the user to choose an account from the accounts list to manage.
    // Lists all available accounts by name and account number for easy selection.
    private static void selectAccount() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available. Please add an account first.");
            return;
        }

        System.out.println("Select an account:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountName()
                    + " (Account Number: " + accounts.get(i).getAccountNumber() + ")");
        }

        int accountChoice = sc.nextInt() - 1;
        if (accountChoice >= 0 && accountChoice < accounts.size()) {
            manageAccount(accounts.get(accountChoice));
        } else {
            System.out.println("Invalid account selection.");
        }
    }

    // Manages a specific account by offering the user options to view, deposit, or withdraw from the account.
    // Uses a loop to keep the account menu open until the user chooses to return to the main menu.
    private static void manageAccount(BankAccount account) {
        int choice;
        do {
            System.out.println("\nAccount Menu for " + account.getAccountName() + "\n"
                    + "\t1. View account information\n"
                    + "\t2. Deposit\n"
                    + "\t3. Withdraw\n"
                    + "\t4. Return to main menu\n"
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
                case 4 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}