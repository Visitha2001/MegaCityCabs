package com.megacity.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

public class DBConnectionTest {

    @Test
    public void testGetConnection() {
        try {
            // Attempt to get a connection
            Connection connection = DBConnection.getConnection();
            
            // Assert that the connection is not null
            assertNotNull(connection, "Connection should not be null");
            
            // Optionally, you can print a success message
            System.out.println("Connection established successfully!");
            
            // Close the connection
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            // If any exception occurs, fail the test
            fail("Exception occurred while trying to get a connection: " + e.getMessage());
        }
    }
}