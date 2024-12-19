package model;

import java.sql.*;

/*
This class manages database connections and operations.
It provides methods to establish a connection and retrieve data
from the database, such as credits for a specific module.
*/
public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/UniversityDB";
    private static final String USER = "root";
    private static final String PASSWORD = "password1234";

    /*
    Establishes a connection to the MySQL database using the provided URL, user, and password.
    Returns a Connection object if successful.
    Throws an SQLException if the connection fails.
    */
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Prints stack trace if the driver is not found
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /*
    Retrieves the credit value for a specific module code from the database.
    Returns the credit value if the module is found, or -1 if no matching module exists.
    */
    public static int getCreditsForModule(String moduleCode) {
        int credits = -1;  // Default value indicating module not found
        try (Connection connection = getConnection()) {
            String query = "SELECT credits FROM Modules WHERE ModuleCode = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, moduleCode); // Set the module code in the query
                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        credits = resultSet.getInt("credits"); // Retrieve the credits value
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Prints stack trace for database-related exceptions
        }
        return credits;
    }
}
