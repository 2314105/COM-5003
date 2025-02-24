package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/*
This class tests the functionality of the DatabaseManager class,
ensuring that database connections are established and handled properly.
*/
class DatabaseManagerTest {

    private DatabaseManager databaseManager;

    /*
    Sets up the test environment by initializing the DatabaseManager before each test case.
    */
    @BeforeEach
    void setUp() {
        // Initialize the DatabaseManager instance (not strictly necessary since methods are static).
        databaseManager = new DatabaseManager();
    }

    /*
    Tests whether a successful connection to the database can be established.
    Verifies that the connection is not null and is valid.
    */
    @Test
    void testGetConnection_Success() {
        try {
            // Act: Attempt to get a connection from the DatabaseManager
            Connection connection = DatabaseManager.getConnection();

            // Assert: Ensure the connection is not null and is valid
            assertNotNull(connection, "Connection should not be null");
            assertTrue(connection.isValid(2), "Connection should be valid"); // Timeout of 2 seconds for validity check

            // Close the connection after testing
            connection.close();
        } catch (SQLException e) {
            // Fail the test if an exception occurs, as the connection should succeed
            fail("Connection to the database should be successful: " + e.getMessage());
        }
    }

    /*
    Tests the behavior of the DatabaseManager when attempting to connect with invalid credentials.
    Simulates a failure by setting incorrect database properties and ensures an exception is thrown.
    */
    @Test
    void testGetConnection_Failure() {
        // Simulate invalid credentials by altering the database URL
        System.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/invalid_database");

        try {
            // Act: Attempt to get a connection with incorrect credentials
            Connection connection = DatabaseManager.getConnection();

            // Assert: Fail the test if no exception is thrown
            fail("Connection should have failed due to invalid database credentials.");
        } catch (SQLException e) {
            // Assert: Check if the exception message indicates a database-related error
            assertTrue(e.getMessage().contains("Unknown database"),
                    "Exception message should indicate a database error.");
        }
    }
}
