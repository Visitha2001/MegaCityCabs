package com.megacity.dao;

import com.megacity.models.Ride;
import com.megacity.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
                        rs.getString("mobile") != null ? rs.getString("mobile") : "",
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
    public void assignRider(int rideId, String riderUsername, String plateNumber, String mobile) throws SQLException {
        String query = "UPDATE rides SET rider_username = ?, vehicle_plate_number = ?, mobile = ?, ride_status = 'ASSIGNED' WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, riderUsername);
            stmt.setString(2, plateNumber);
            stmt.setString(3, mobile);
            stmt.setInt(4, rideId);
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
                        rs.getString("mobile") != null ? rs.getString("mobile") : "",
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
                ride.setMobile(resultSet.getString("mobile"));
                ride.setRideStartedAt(resultSet.getObject("ride_started_at", LocalDateTime.class));
                ride.setRideEndedAt(resultSet.getObject("ride_ended_at", LocalDateTime.class));

                rides.add(ride);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return rides;
    }
    
    // Method to get all assigned rides for a specific rider
    public List<Ride> getRidesForRiders(String username) throws SQLException {
        List<Ride> rides = new ArrayList<>();
        String query = "SELECT * FROM rides WHERE rider_username = ? AND ride_status = 'ASSIGNED'";

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
    
    // Method to get all accepted rides for a specific rider
    public List<Ride> getAcceptedRidesForRiders(String username) throws SQLException {
        List<Ride> rides = new ArrayList<>();
        String query = "SELECT * FROM rides WHERE rider_username = ? AND ride_status = 'ACCEPTED'";

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

    
    // Method to update ride status
    public void updateRideStatus(int rideId, String status, LocalDateTime rideStartedAt, LocalDateTime rideEndedAt) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE rides SET ride_status = ?");
        
        // Append ride_started_at to the query if it is provided
        if (rideStartedAt != null) {
            query.append(", ride_started_at = ?");
        }
        // Append ride_ended_at to the query if it is provided
        if (rideEndedAt != null) {
            query.append(", ride_ended_at = ?");
        }
        query.append(" WHERE id = ?");
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, status);
            
            // Set ride_started_at if provided
            if (rideStartedAt != null) {
                stmt.setTimestamp(parameterIndex++, Timestamp.valueOf(rideStartedAt));
            }
            // Set ride_ended_at if provided
            if (rideEndedAt != null) {
                stmt.setTimestamp(parameterIndex++, Timestamp.valueOf(rideEndedAt));
            }
            // Set rideId
            stmt.setInt(parameterIndex, rideId);
            
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
    
//    -----------------------------------------------
    
    // Method to get total number of rides
    public int getTotalRidesCount() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM rides";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int total = rs.getInt("total");
                System.out.println("Total Rides: " + total); // Debugging
                return total;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return 0;
    }

    // Method to get total income from all completed rides
    public double getTotalIncome() throws SQLException {
        String query = "SELECT SUM(price) AS total_income FROM rides WHERE ride_status = 'COMPLETED'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getDouble("total_income");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return 0.0;
    }

    // Method to get total number of completed rides
    public int getCompletedRidesCount() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM rides WHERE ride_status = 'COMPLETED'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return 0;
    }

    // Method to get total number of requested rides
    public int getRequestedRidesCount() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM rides WHERE ride_status = 'REQUESTED'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return 0;
    }

    // Method to get total number of assigned rides
    public int getAssignedRidesCount() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM rides WHERE ride_status = 'ASSIGNED'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return 0;
    }

    // Method to get recent rides for dashboard
    public List<Ride> getRecentRides(int limit) throws SQLException {
        String query = "SELECT * FROM rides ORDER BY id DESC LIMIT ?";
        List<Ride> recentRides = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
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
                    rs.getString("mobile") != null ? rs.getString("mobile") : "",
                    rs.getTimestamp("ride_started_at") != null ? rs.getTimestamp("ride_started_at").toLocalDateTime() : null,
                    rs.getTimestamp("ride_ended_at") != null ? rs.getTimestamp("ride_ended_at").toLocalDateTime() : null
                );
                recentRides.add(ride);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        return recentRides;
    }
    
    // method to get completed rides by specific rider
    public List<Ride> getCompletedRidesByRider(String username) throws SQLException {
        List<Ride> completedRides = new ArrayList<>();
        String query = "SELECT * FROM rides WHERE rider_username = ? AND ride_status = 'COMPLETED'";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username); // Set the username parameter
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ride ride = new Ride(
                    resultSet.getInt("id"),
                    resultSet.getString("start_location"),
                    resultSet.getString("end_location"),
                    resultSet.getString("customer_username"),
                    resultSet.getString("rider_username") != null ? resultSet.getString("rider_username") : "",
                    resultSet.getDouble("price"),
                    resultSet.getDouble("length_of_ride"),
                    resultSet.getString("ride_status"),
                    resultSet.getString("vehicle_type"),
                    resultSet.getString("vehicle_plate_number") != null ? resultSet.getString("vehicle_plate_number") : "",
                    resultSet.getString("mobile") != null ? resultSet.getString("mobile") : "",
                    resultSet.getTimestamp("ride_started_at") != null ? resultSet.getTimestamp("ride_started_at").toLocalDateTime() : null,
                    resultSet.getTimestamp("ride_ended_at") != null ? resultSet.getTimestamp("ride_ended_at").toLocalDateTime() : null
                );
                completedRides.add(ride);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        return completedRides;
    }
}