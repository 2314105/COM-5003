import java.util.ArrayList;
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

    // Getters
    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
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

            switch (choice) {
                case 1 -> addAccount();
                case 2 -> selectAccount();
                case 3 -> System.out.println("Thank you for using our service!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        sc.close(); // Close the scanner
    }

    // Method to add a new account
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

    // Method to select an account from the list
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

    // Method to manage a specific account
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