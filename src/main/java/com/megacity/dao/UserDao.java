package com.megacity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.megacity.models.User;
import com.megacity.utils.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
    
    // Method to get all riders filtered by vehicle type
    public List<User> getRidersByVehicleType(String vehicleType) {
        String query = "SELECT * FROM users WHERE role = 'rider' AND vehicleType = ?";
        List<User> riders = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vehicleType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User rider = new User(
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
                riders.add(rider);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return riders;
    }
    
    // get all riders
    public List<User> getAllRiders() {
        String query = "SELECT * FROM users WHERE role = 'rider'";
        List<User> riders = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User rider = new User(
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
                riders.add(rider);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return riders;
    }
    
    // get all customers
    public List<User> getAllCustomers() {
        String query = "SELECT * FROM users WHERE role = 'customer'";
        List<User> customers = new ArrayList<>();
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the result set and create User objects
            while (resultSet.next()) {
                User customer = new User(
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
                customers.add(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return customers;
    }
    
    public boolean deleteRider(String username) {
        String query = "DELETE FROM users WHERE username = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
//    -------------------------------------------
    
 // Method to get counts of riders by vehicle type
    public Map<String, Integer> getRiderCountsByVehicleType() throws SQLException {
        String query = "SELECT vehicleType, COUNT(*) as count FROM users WHERE role = 'rider' GROUP BY vehicleType";
        Map<String, Integer> vehicleTypeCounts = new HashMap<>();
        
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            while (resultSet.next()) {
                String vehicleType = resultSet.getString("vehicleType");
                int count = resultSet.getInt("count");
                vehicleTypeCounts.put(vehicleType, count);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
        
        return vehicleTypeCounts;
    }

    // Method to get total number of riders
    public int getTotalRidersCount() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM users WHERE role = 'rider'";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
        
        return 0;
    }

    // Method to get total number of customers
    public int getTotalCustomersCount() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM users WHERE role = 'customer'";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
        
        return 0;
    }
}