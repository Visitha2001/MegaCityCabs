package com.megacity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.megacity.models.User;
import com.megacity.utils.DBConnection;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // Method to register a new user in the database
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, email, password, mobile, NIC, address, role, vehicleType, plateNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getMobile());
            preparedStatement.setString(5, user.getNic());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setString(8, user.getVehicleType());
            preparedStatement.setString(9, user.getPlateNumber());

            int rowCount = preparedStatement.executeUpdate();
            return rowCount > 0; // Return true if insertion successful

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false; // Return false if exception occurs
        }
    }

    // Method to authenticate a user
    public User authenticateUser(String usernameOrEmail, String password) {
        String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usernameOrEmail);
            preparedStatement.setString(2, usernameOrEmail);
            preparedStatement.setString(3, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("mobile"),
                        resultSet.getString("NIC"),
                        resultSet.getString("address"),
                        resultSet.getString("role"),
                        resultSet.getString("vehicleType"),
                        resultSet.getString("plateNumber")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}