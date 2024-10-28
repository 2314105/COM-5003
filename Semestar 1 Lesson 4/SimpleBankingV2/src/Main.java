import java.util.Scanner;

/*
The BankAccount class manages withdrawing, depositing, and viewing account information.
By making this a class, the application becomes more scalable for future development,
such as adding more accounts or features.
*/

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

    /*
    Method to view account information in a formatted manner.
    The formatting aligns text to specific widths:
    - The name is aligned to 20 characters,
    - The account number is aligned to 10 characters,
    - The balance is formatted to two decimal places.
    This improves readability when displaying account information.
    */
    public void viewInformation() {
        System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
        System.out.println("-".repeat(40));
        System.out.printf("%-20s %-10s $%.2f %n", accountName, accountNumber, accountBalance);
    }

    // Method to deposit an amount into the account
    public void deposit(double amount) {
        // Validates that the deposit amount is positive before updating the balance
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Successfully deposited $" + amount);
            System.out.println("Updated Balance: $" + accountBalance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw an amount from the account
    public void withdraw(double amount) {
        // Validates that the withdrawal amount is positive and does not exceed the account balance
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
        // Creates a BankAccount object with initial account details, providing a single account for this example
        BankAccount account = new BankAccount("John Doe", "123456789", 1000.00);
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            // Display menu to the user
            System.out.println("\nWelcome, " + account.getAccountName() + "\n"
                    + "\t1. View account information \n"
                    + "\t2. Deposit \n"
                    + "\t3. Withdraw \n"
                    + "\t4. Exit \n"
                    + "Enter your choice: ");
            choice = sc.nextInt();

            // Uses a switch statement for menu selection, directing the flow of the program based on user input
            switch (choice) {
                case 1 -> account.viewInformation(); // Calls the method to view account information
                case 2 -> {
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount); // Calls the deposit method with the user-specified amount
                }
                case 3 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount); // Calls the withdraw method with the user-specified amount
                }
                case 4 -> System.out.println("Thank you for using our service!"); // Exit message
                default -> System.out.println("Invalid choice. Please try again."); // Handles invalid input
            }
        } while (choice != 4); // Loop continues until the user chooses to exit

        sc.close(); // Closes the scanner resource once the program is done
    }
}