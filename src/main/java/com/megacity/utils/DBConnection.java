package com.megacity.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database details
    private static final String URL = "jdbc:mysql://localhost:3306/signup"; // Update with your DB info
    private static final String USER = "root"; // DB username
    private static final String PASSWORD = "Visitha2001@#"; // DB password

    // Static method to get a connection
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Return the connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}