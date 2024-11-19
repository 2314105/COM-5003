package dev.josh;

public class BankAccount {
    private String accountName;
    private String accountNumber;
    private double accountBalance;

    public BankAccount(String accountName, String accountNumber, double accountBalance) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    // Getter for accountName
    public String getAccountName() {
        return accountName;
    }

    // Getter for accountNumber
    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter for accountBalance
    public double getAccountBalance() {
        return accountBalance;
    }

    // Setter for accountBalance (if needed)
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    // Method to view account information
    public void viewInformation() {
        System.out.printf("Account Name: %s\nAccount Number: %s\nBalance: $%.2f\n",
                accountName, accountNumber, accountBalance);
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            System.out.printf("Deposited $%.2f. New balance: $%.2f\n", amount, accountBalance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            System.out.printf("Withdrew $%.2f. New balance: $%.2f\n", amount, accountBalance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
}