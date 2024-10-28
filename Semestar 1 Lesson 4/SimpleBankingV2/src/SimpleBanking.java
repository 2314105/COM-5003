import java.util.Scanner;

public class SimpleBanking {
    // Instance variables for account information
    private String accountName;
    private String accountNumber;
    private double accountBalance;

    // Scanner object
    private Scanner sc = new Scanner(System.in);

    // Constructor to initialize account details
    public SimpleBanking(String name, String number, double balance) {
        this.accountName = name;
        this.accountNumber = number;
        this.accountBalance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void deposit() {
        System.out.print("Enter deposit amount: ");
        int amount = sc.nextInt();
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Successfully deposited $" + amount);
            System.out.println("Updated Balance: $" + accountBalance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        int amount = sc.nextInt();
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            System.out.println("Updated Balance: $" + accountBalance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void exit() {
        System.out.println("Thank you for using our service!");
    }

    public void viewInformation() {
        // Display account information
        System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
        System.out.println("-".repeat(40));
        System.out.printf("%-20s %-10s $%.2f %n", accountName, accountNumber, accountBalance);
    }
}