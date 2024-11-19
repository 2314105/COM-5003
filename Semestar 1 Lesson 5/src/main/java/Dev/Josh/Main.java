package dev.josh;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the database
        DatabaseInitializer.initialize();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Create Account \n2. Manage Account \n3. Exit \nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount(sc);  // Creating a new account
                case 2 -> manageAccount(sc);  // Managing an existing account
                case 3 -> System.out.println("Thank you for using our service!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    // Method to create a new account
    public static void createAccount(Scanner sc) {
        System.out.print("Enter account holder's name: ");
        sc.nextLine(); // Consume newline
        String name = sc.nextLine();
        System.out.print("Enter account number: ");
        String number = sc.next();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        BankAccount account = new BankAccount(name, number, balance);
        BankAccountDAO.createAccount(account);  // Save the account to the database
    }

    // Method to manage an existing account
    public static void manageAccount(Scanner sc) {
        System.out.print("Enter account number: ");
        String number = sc.next();

        BankAccount account = BankAccountDAO.getAccount(number);  // Fetch the account from DB
        if (account != null) {
            int choice;
            do {
                System.out.println("\n1. View Account \n2. Deposit \n3. Withdraw \n4. Exit \nEnter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> account.viewInformation();  // View account info
                    case 2 -> {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        account.deposit(depositAmount);  // Deposit to the account
                        BankAccountDAO.updateAccount(account);  // Update the database
                    }
                    case 3 -> {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = sc.nextDouble();
                        account.withdraw(withdrawalAmount);  // Withdraw from the account
                        BankAccountDAO.updateAccount(account);  // Update the database
                    }
                    case 4 -> System.out.println("Exiting account management.");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        } else {
            System.out.println("Account not found.");
        }
    }
}