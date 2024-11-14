import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Create Account \n2. Manage Account \n3. Exit \nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount(accounts, sc);
                case 2 -> manageAccount(accounts, sc);
                case 3 -> System.out.println("Thank you for using our service!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    public static void createAccount(ArrayList<BankAccount> accounts, Scanner sc) {
        System.out.print("Enter account holder's name: ");
        sc.nextLine(); // Consume newline
        String name = sc.nextLine();
        System.out.print("Enter account number: ");
        String number = sc.next();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();
        accounts.add(new BankAccount(name, number, balance));
        System.out.println("Account created successfully!");
    }

    public static void manageAccount(ArrayList<BankAccount> accounts, Scanner sc) {
        System.out.print("Enter account number: ");
        String number = sc.next();
        BankAccount account = accounts.stream()
                .filter(acc -> acc.getAccountNumber().equals(number))
                .findFirst()
                .orElse(null);

        if (account != null) {
            int choice;
            do {
                System.out.println("\n1. View Account \n2. Deposit \n3. Withdraw \n4. Exit \nEnter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> account.viewInformation();
                    case 2 -> account.deposit(sc);
                    case 3 -> account.withdraw(sc);
                    case 4 -> System.out.println("Exiting account management.");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        } else {
            System.out.println("Account not found.");
        }
    }
}

class BankAccount {
    private String accountName;
    private String accountNumber;
    private double accountBalance;

    public BankAccount(String name, String number, double balance) {
        this.accountName = name;
        this.accountNumber = number;
        this.accountBalance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(Scanner sc) {
        System.out.print("Enter deposit amount: ");
        int amount = sc.nextInt();
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(Scanner sc) {
        System.out.print("Enter withdrawal amount: ");
        int amount = sc.nextInt();
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void viewInformation() {
        System.out.printf("Account: %s, Number: %s, Balance: $%.2f\n", accountName, accountNumber, accountBalance);
    }
}