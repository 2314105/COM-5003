package dev.josh;

import java.sql.*;

public class DatabaseInitializer {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    /*
      Initializes the database and creates the 'accounts' table if it doesn't exist.
     */
    public static void initialize() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "account_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "account_name VARCHAR(255)," +
                    "account_number VARCHAR(20) UNIQUE," +
                    "account_balance DOUBLE)";

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("Database initialized!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}