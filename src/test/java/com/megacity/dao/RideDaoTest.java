package com.megacity.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.megacity.models.Ride;
import com.megacity.utils.DBConnection;

class RideDaoTest {

    private RideDao rideDao;

    @BeforeEach
    void setUp() throws SQLException {
        rideDao = new RideDao();
        System.out.println("RideDaoTest setup complete.");

        // Clean up the database before each test
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM rides"); // Delete all rows from the rides table
            stmt.executeUpdate("ALTER TABLE rides AUTO_INCREMENT = 1"); // Reset auto-increment
            System.out.println("Database cleaned up and auto-increment reset.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    @Test
    void testBookRide() throws SQLException {
        System.out.println("Running testBookRide...");

        Ride ride = new Ride();
        ride.setStart_location("Location A");
        ride.setEnd_location("Location B");
        ride.setCustomer_username("customer1");
        ride.setPrice(20.0);
        ride.setLengthOfRide(5.0);
        ride.setRideStatus("REQUESTED");
        ride.setVehicleType("Car");

        rideDao.bookRide(ride);
        System.out.println("Ride booked successfully.");

        List<Ride> requestedRides = rideDao.getRequestedRides();
        assertFalse(requestedRides.isEmpty());
        assertEquals("Location A", requestedRides.get(0).getStart_location());

        System.out.println("testBookRide completed successfully.");
    }

    @Test
    void testUpdateRideStatus() throws SQLException {
        System.out.println("Running testUpdateRideStatus...");

        // Step 1: Book a ride
        Ride ride = new Ride();
        ride.setStart_location("Location A");
        ride.setEnd_location("Location B");
        ride.setCustomer_username("customer1");
        ride.setPrice(20.0);
        ride.setLengthOfRide(5.0);
        ride.setRideStatus("REQUESTED");
        ride.setVehicleType("Car");

        rideDao.bookRide(ride);
        System.out.println("Ride booked successfully.");

        // Step 2: Assign a rider to the ride
        List<Ride> requestedRides = rideDao.getRequestedRides();
        assertFalse(requestedRides.isEmpty());
        int rideId = requestedRides.get(0).getId(); // Get the ID of the booked ride

        rideDao.assignRider(rideId, "rider1", "ABC123", "1234567890");
        System.out.println("Rider assigned successfully.");

        // Step 3: Update the ride status to COMPLETED
        LocalDateTime now = LocalDateTime.now();
        rideDao.updateRideStatus(rideId, "COMPLETED", now, now);
        System.out.println("Ride status updated successfully.");

        // Step 4: Verify the ride status
        List<Ride> completedRides = rideDao.getCompletedRidesByRider("rider1");
        assertFalse(completedRides.isEmpty());
        assertEquals("COMPLETED", completedRides.get(0).getRideStatus());

        System.out.println("testUpdateRideStatus completed successfully.");
    }
}