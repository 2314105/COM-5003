package model;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/UniversityDB";
    private static final String USER = "root";
    private static final String PASSWORD = "password1234";

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver (optional, depending on the version)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to get credits for a given module code
    public static int getCreditsForModule(String moduleCode) {
        int credits = -1;  // Default to -1 if no module is found
        try (Connection connection = getConnection()) {
            String query = "SELECT credits FROM Modules WHERE ModuleCode = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, moduleCode);
                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        credits = resultSet.getInt("credits");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credits;
    }
}