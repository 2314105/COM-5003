import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        /* Main menu loop for user interaction */
        do {
            System.out.println("\n1. Create Account \n2. Manage Account \n3. Admin Options \n4. Exit \nEnter your choice: ");
            choice = sc.nextInt();

            /* Switch for menu options */
            switch (choice) {
                case 1 -> createAccount(sc);
                case 2 -> manageAccount(sc);
                case 3 -> adminOptions(sc); // Admin options for inserting, deleting, and updating
                case 4 -> System.out.println("Thank you for using our service!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    /* Method to create a new bank account */
    public static void createAccount(Scanner sc) {
        System.out.print("Enter account holder's name: ");
        sc.nextLine(); // Consume newline
        String name = sc.nextLine();
        System.out.print("Enter account number: ");
        String number = sc.next();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        /* Add the new account to the database */
        BankAccount.createAccount(name, number, balance);
    }

    /* Method to manage an existing bank account */
    public static void manageAccount(Scanner sc) {
        System.out.print("Enter account number: ");
        String number = sc.next();

        /* Find the account by number */
        BankAccount account = BankAccount.getAccountByNumber(number);

        if (account != null) {
            int choice;

            /* Account management menu loop */
            do {
                System.out.println("\n1. View Account \n2. Deposit \n3. Withdraw \n4. Exit \nEnter your choice: ");
                choice = sc.nextInt();

                /* Switch for account management options */
                switch (choice) {
                    case 1 -> account.viewInformation();
                    case 2 -> {
                        System.out.print("Enter deposit amount: ");
                        double amount = sc.nextDouble();
                        account.deposit(amount);
                    }
                    case 3 -> {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = sc.nextDouble();
                        account.withdraw(amount);
                    }
                    case 4 -> System.out.println("Exiting account management.");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        } else {
            System.out.println("Account not found.");
        }
    }

    /* Admin options for inserting, deleting, and updating accounts */
    public static void adminOptions(Scanner sc) {
        int choice;

        do {
            System.out.println("\nAdmin Options: ");
            System.out.println("1. Insert Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Update Account");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> insertAccount(sc); // Method to insert a new account
                case 2 -> deleteAccount(sc); // Method to delete an account
                case 3 -> updateAccount(sc); // Method to update an account
                case 4 -> System.out.println("Returning to main menu.");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    /* Method to insert a new account as admin */
    public static void insertAccount(Scanner sc) {
        System.out.print("Enter account holder's name: ");
        sc.nextLine(); // Consume newline
        String name = sc.nextLine();
        System.out.print("Enter account number: ");
        String number = sc.next();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        /* Add the new account to the database */
        BankAccount.createAccount(name, number, balance);
    }

    /* Method to delete an account as admin */
    public static void deleteAccount(Scanner sc) {
        System.out.print("Enter account number to delete: ");
        String number = sc.next();

        /* Attempt to delete the account */
        boolean success = BankAccount.deleteAccount(number);
        if (success) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found or unable to delete.");
        }
    }

    /* Method to update an account's information as admin */
    public static void updateAccount(Scanner sc) {
        System.out.print("Enter account number to update: ");
        String number = sc.next();

        /* Find the account */
        BankAccount account = BankAccount.getAccountByNumber(number);
        if (account != null) {
            System.out.println("1. Update Name");
            System.out.println("2. Update Balance");
            System.out.print("Enter your choice: ");
            int updateChoice = sc.nextInt();

            switch (updateChoice) {
                case 1 -> {
                    System.out.print("Enter new account holder's name: ");
                    sc.nextLine(); // Consume newline
                    String newName = sc.nextLine();
                    account.updateName(newName); // Update the account name in the database
                }
                case 2 -> {
                    System.out.print("Enter new account balance: ");
                    double newBalance = sc.nextDouble();
                    account.updateBalance(newBalance); // Update the balance in the database
                }
                default -> System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}