package com.megacity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.megacity.models.User;
import com.megacity.utils.DBConnection;

public class UserDao {

    // Method to register a new user in the database
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, email, password, mobile) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getMobile());

            int rowCount = preparedStatement.executeUpdate();
            return rowCount > 0; // Return true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an SQL exception occurs
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false; // Return false if the JDBC driver is not found
        }
    }
}