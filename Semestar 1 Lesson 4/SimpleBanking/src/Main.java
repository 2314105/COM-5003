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
    }
}

class SimpleBanking {
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