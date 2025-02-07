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
        String query = "INSERT INTO rides (start_location, end_location, customer_username, price, length_of_ride, ride_status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ride.getStart_location());
            stmt.setString(2, ride.getEnd_location());
            stmt.setString(3, ride.getCustomer_username());
            stmt.setDouble(4, ride.getPrice());
            stmt.setDouble(5, ride.getLengthOfRide());
            stmt.setString(6, ride.getRideStatus());
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    // Method for admin to assign a rider
    public void assignRider(int rideId, String riderUsername, String vehicleType, String vehiclePlateNumber) throws SQLException {
        String query = "UPDATE rides SET rider_username = ?, vehicle_type = ?, vehicle_plate_number = ?, ride_status = 'ASSIGNED' WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, riderUsername);
            stmt.setString(2, vehicleType);
            stmt.setString(3, vehiclePlateNumber);
            stmt.setInt(4, rideId);
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    // Method for rider to accept a ride
    public void acceptRide(int rideId) throws SQLException {
        String query = "UPDATE rides SET ride_status = 'ACCEPTED' WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, rideId);
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    // Method to fetch rides for a specific rider
    public List<Ride> getRidesForRider(String riderUsername) throws SQLException {
        List<Ride> rides = new ArrayList<>();
        String query = "SELECT * FROM rides WHERE rider_username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, riderUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ride ride = new Ride();
                ride.setStart_location(rs.getString("start_location"));
                ride.setEnd_location(rs.getString("end_location"));
                ride.setCustomer_username(rs.getString("customer_username"));
                ride.setRider_username(rs.getString("rider_username"));
                ride.setPrice(rs.getDouble("price"));
                ride.setLengthOfRide(rs.getDouble("length_of_ride"));
                ride.setRideStatus(rs.getString("ride_status"));
                ride.setVehicleType(rs.getString("vehicle_type"));
                ride.setVehiclePlateNumber(rs.getString("vehicle_plate_number"));
                ride.setRideStartedAt(rs.getTimestamp("ride_started_at") != null ? rs.getTimestamp("ride_started_at").toLocalDateTime() : null);
                ride.setRideEndedAt(rs.getTimestamp("ride_ended_at") != null ? rs.getTimestamp("ride_ended_at").toLocalDateTime() : null);
                rides.add(ride);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return rides;
    }

    // Method to fetch rides for a specific customer
    public List<Ride> getRidesForCustomer(String customerUsername) throws SQLException {
        List<Ride> rides = new ArrayList<>();
        String query = "SELECT * FROM rides WHERE customer_username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customerUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ride ride = new Ride();
                ride.setStart_location(rs.getString("start_location"));
                ride.setEnd_location(rs.getString("end_location"));
                ride.setCustomer_username(rs.getString("customer_username"));
                ride.setRider_username(rs.getString("rider_username"));
                ride.setPrice(rs.getDouble("price"));
                ride.setLengthOfRide(rs.getDouble("length_of_ride"));
                ride.setRideStatus(rs.getString("ride_status"));
                ride.setVehicleType(rs.getString("vehicle_type"));
                ride.setVehiclePlateNumber(rs.getString("vehicle_plate_number"));
                ride.setRideStartedAt(rs.getTimestamp("ride_started_at") != null ? rs.getTimestamp("ride_started_at").toLocalDateTime() : null);
                ride.setRideEndedAt(rs.getTimestamp("ride_ended_at") != null ? rs.getTimestamp("ride_ended_at").toLocalDateTime() : null);
                rides.add(ride);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return rides;
    }
}