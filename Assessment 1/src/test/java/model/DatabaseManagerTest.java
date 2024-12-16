package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {

    private DatabaseManager databaseManager;

    @BeforeEach
    void setUp() {
        // Initialize the DatabaseManager before each test
        databaseManager = new DatabaseManager();
    }

    @Test
    void testGetConnection_Success() {
        try {
            // Attempt to get a connection
            Connection connection = DatabaseManager.getConnection();
            assertNotNull(connection, "Connection should not be null");
            assertTrue(connection.isValid(2), "Connection should be valid");
            connection.close(); // Close the connection after use
        } catch (SQLException e) {
            fail("Connection to the database should be successful: " + e.getMessage());
        }
    }

    @Test
    void testGetConnection_Failure() {
        // Simulate invalid credentials by altering the URL or credentials (use wrong values)
        System.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/invalid_database");

        try {
            // Attempt to get a connection with incorrect credentials
            Connection connection = DatabaseManager.getConnection();
            fail("Connection should have failed due to invalid database credentials.");
        } catch (SQLException e) {
            assertTrue(e.getMessage().contains("Unknown database"), "Exception message should indicate a database error.");
        }
    }
}