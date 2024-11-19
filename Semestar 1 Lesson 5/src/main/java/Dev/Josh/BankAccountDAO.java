package dev.josh;

import java.sql.*;

public class BankAccountDAO {
    private static final String URL = "jdbc:h2:~/test"; // Use the H2 database
    private static final String USER = "sa";  // Default user
    private static final String PASSWORD = ""; // Default password

    public static void createAccount(BankAccount account) {
        String query = "INSERT INTO accounts (account_name, account_number, account_balance) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, account.getAccountName());
            stmt.setString(2, account.getAccountNumber());
            stmt.setDouble(3, account.getAccountBalance());
            stmt.executeUpdate();

            System.out.println("Account successfully created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static BankAccount getAccount(String accountNumber) {
        String query = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("account_name");
                double balance = rs.getDouble("account_balance");
                return new BankAccount(name, accountNumber, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateAccount(BankAccount account) {
        String query = "UPDATE accounts SET account_balance = ? WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, account.getAccountBalance());
            stmt.setString(2, account.getAccountNumber());
            stmt.executeUpdate();

            System.out.println("Account updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}