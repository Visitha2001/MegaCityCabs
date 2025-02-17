package com.megacity.dao;

import com.megacity.models.Ride;
import com.megacity.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RideDao {

    // Method to book a ride
    public void bookRide(Ride ride) throws SQLException {
        String query = "INSERT INTO rides (start_location, end_location, customer_username, price, length_of_ride, ride_status, vehicle_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ride.getStart_location());
            stmt.setString(2, ride.getEnd_location());
            stmt.setString(3, ride.getCustomer_username());
            stmt.setDouble(4, ride.getPrice());
            stmt.setDouble(5, ride.getLengthOfRide());
            stmt.setString(6, ride.getRideStatus());
            stmt.setString(7, ride.getVehicleType());
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    // Method to get all rides with status "REQUESTED"
    public List<Ride> getRequestedRides() throws SQLException {
        String query = "SELECT * FROM rides WHERE ride_status = ?";
        List<Ride> requestedRides = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "REQUESTED");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ride ride = new Ride(
                        rs.getInt("id"),
                        rs.getString("start_location"),
                        rs.getString("end_location"),
                        rs.getString("customer_username"),
                        rs.getString("rider_username") != null ? rs.getString("rider_username") : "",
                        rs.getDouble("price"),
                        rs.getDouble("length_of_ride"),
                        rs.getString("ride_status"),
                        rs.getString("vehicle_type"),
                        rs.getString("vehicle_plate_number") != null ? rs.getString("vehicle_plate_number") : "",
                        rs.getTimestamp("ride_started_at") != null ? rs.getTimestamp("ride_started_at").toLocalDateTime() : null,
                        rs.getTimestamp("ride_ended_at") != null ? rs.getTimestamp("ride_ended_at").toLocalDateTime() : null
                );
                requestedRides.add(ride);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return requestedRides;
    }
    
    // Method to assign a rider to a ride
    public void assignRider(int rideId, String riderUsername, String plateNumber) throws SQLException {
        String query = "UPDATE rides SET rider_username = ?, vehicle_plate_number = ?, ride_status = 'ASSIGNED' WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, riderUsername);
            stmt.setString(2, plateNumber);
            stmt.setInt(3, rideId);
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
    
    // Method to get all rides with status "ASSIGNED"
    public List<Ride> getAssignedRides() throws SQLException {
        String query = "SELECT * FROM rides WHERE ride_status = ?";
        List<Ride> assignedRides = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "ASSIGNED");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ride ride = new Ride(
                        rs.getInt("id"),
                        rs.getString("start_location"),
                        rs.getString("end_location"),
                        rs.getString("customer_username"),
                        rs.getString("rider_username") != null ? rs.getString("rider_username") : "",
                        rs.getDouble("price"),
                        rs.getDouble("length_of_ride"),
                        rs.getString("ride_status"),
                        rs.getString("vehicle_type"),
                        rs.getString("vehicle_plate_number") != null ? rs.getString("vehicle_plate_number") : "",
                        rs.getTimestamp("ride_started_at") != null ? rs.getTimestamp("ride_started_at").toLocalDateTime() : null,
                        rs.getTimestamp("ride_ended_at") != null ? rs.getTimestamp("ride_ended_at").toLocalDateTime() : null
                );
                assignedRides.add(ride);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return assignedRides;
    }
    
    // Method to get all rides for a specific user
    public List<Ride> getRidesForUser(String username) throws SQLException {
        List<Ride> rides = new ArrayList<>();
        String query = "SELECT * FROM rides WHERE customer_username = ?"; // Adjust the table name and column names as needed

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username); // Set the username parameter
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ride ride = new Ride();
                ride.setId(resultSet.getInt("id"));
                ride.setStart_location(resultSet.getString("start_location"));
                ride.setEnd_location(resultSet.getString("end_location"));
                ride.setCustomer_username(resultSet.getString("customer_username"));
                ride.setPrice(resultSet.getDouble("price"));
                ride.setLengthOfRide(resultSet.getDouble("length_of_ride"));
                ride.setVehicleType(resultSet.getString("vehicle_type"));
                ride.setRideStatus(resultSet.getString("ride_status"));
                ride.setRider_username(resultSet.getString("rider_username"));
                ride.setVehiclePlateNumber(resultSet.getString("vehicle_plate_number"));

                rides.add(ride);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return rides;
    }
}