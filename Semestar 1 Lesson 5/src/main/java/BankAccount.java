import java.sql.*;

public class BankAccount {
    private String accountName;
    private String accountNumber;
    private double accountBalance;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BankDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password1234";

    /* Constructor to initialize a bank account */
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

    public double getAccountBalance() {
        return accountBalance;
    }

    /* Method to create a bank account in the database */
    public static void createAccount(String name, String number, double balance) {
        String insertSQL = "INSERT INTO accounts (account_number, account_name, account_balance) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, number);
            pstmt.setString(2, name);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();
            System.out.println("Account created successfully in the database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Method to retrieve an account by account number */
    public static BankAccount getAccountByNumber(String accountNumber) {
        String selectSQL = "SELECT account_name, account_balance FROM accounts WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("account_name");
                double balance = rs.getDouble("account_balance");
                return new BankAccount(name, accountNumber, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Account not found
    }

    /* Method to update account balance */
    public void updateBalance(double newBalance) {
        String updateSQL = "UPDATE accounts SET account_balance = ? WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            this.accountBalance = newBalance;
            System.out.println("Account balance updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Method to display account information */
    public void viewInformation() {
        System.out.printf("Account: %s, Number: %s, Balance: $%.2f\n", accountName, accountNumber, accountBalance);
    }

    /* Method to deposit money */
    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            updateBalance(accountBalance); // Update in DB
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    /* Method to withdraw money */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            updateBalance(accountBalance); // Update in DB
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    /* Method to delete a bank account */
    public static boolean deleteAccount(String accountNumber) {
        String deleteSQL = "DELETE FROM accounts WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, accountNumber);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if the account was deleted

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if the account was not deleted
    }

    /* Method to update the account name */
    public void updateName(String newName) {
        String updateSQL = "UPDATE accounts SET account_name = ? WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            this.accountName = newName;
            System.out.println("Account name updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}