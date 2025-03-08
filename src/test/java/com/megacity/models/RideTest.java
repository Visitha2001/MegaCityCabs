package com.megacity.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class RideTest {

    @Test
    void testRideConstructorAndGetters() {
        System.out.println("Running testRideConstructorAndGetters...");

        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = startedAt.plusHours(1);

        Ride ride = new Ride(20, "Start Location", "End Location", "customer1", "rider1", 25.0, 10.5, "Completed", "Car", "ABC-123", "1234567890", startedAt, endedAt);

        System.out.println("Testing Ride object created with constructor...");

        assertEquals(20, ride.getId());
        System.out.println("ID: " + ride.getId() + " - Test Passed");

        assertEquals("Start Location", ride.getStart_location());
        System.out.println("Start Location: " + ride.getStart_location() + " - Test Passed");

        assertEquals("End Location", ride.getEnd_location());
        System.out.println("End Location: " + ride.getEnd_location() + " - Test Passed");

        assertEquals("customer1", ride.getCustomer_username());
        System.out.println("Customer Username: " + ride.getCustomer_username() + " - Test Passed");

        assertEquals("rider1", ride.getRider_username());
        System.out.println("Rider Username: " + ride.getRider_username() + " - Test Passed");

        assertEquals(25.0, ride.getPrice());
        System.out.println("Price: " + ride.getPrice() + " - Test Passed");

        assertEquals(10.5, ride.getLengthOfRide());
        System.out.println("Length of Ride: " + ride.getLengthOfRide() + " - Test Passed");

        assertEquals("Completed", ride.getRideStatus());
        System.out.println("Ride Status: " + ride.getRideStatus() + " - Test Passed");

        assertEquals("Car", ride.getVehicleType());
        System.out.println("Vehicle Type: " + ride.getVehicleType() + " - Test Passed");

        assertEquals("ABC-123", ride.getVehiclePlateNumber());
        System.out.println("Vehicle Plate Number: " + ride.getVehiclePlateNumber() + " - Test Passed");

        assertEquals("1234567890", ride.getMobile());
        System.out.println("Mobile: " + ride.getMobile() + " - Test Passed");

        assertEquals(startedAt, ride.getRideStartedAt());
        System.out.println("Ride Started At: " + ride.getRideStartedAt() + " - Test Passed");

        assertEquals(endedAt, ride.getRideEndedAt());
        System.out.println("Ride Ended At: " + ride.getRideEndedAt() + " - Test Passed");

        System.out.println("testRideConstructorAndGetters completed successfully.");
    }

    @Test
    void testRideSetters() {
        System.out.println("Running testRideSetters...");

        Ride ride = new Ride();
        ride.setId(21);
        ride.setStart_location("New Start Location");
        ride.setEnd_location("New End Location");
        ride.setCustomer_username("customer2");
        ride.setRider_username("rider2");
        ride.setPrice(30.0);
        ride.setLengthOfRide(15.5);
        ride.setRideStatus("In Progress");
        ride.setVehicleType("Bike");
        ride.setVehiclePlateNumber("XYZ-789");
        ride.setMobile("0987654321");

        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = startedAt.plusHours(1);
        ride.setRideStartedAt(startedAt);
        ride.setRideEndedAt(endedAt);

        System.out.println("Testing Ride object after setting values...");

        assertEquals(21, ride.getId());
        System.out.println("ID: " + ride.getId() + " - Test Passed");

        assertEquals("New Start Location", ride.getStart_location());
        System.out.println("Start Location: " + ride.getStart_location() + " - Test Passed");

        assertEquals("New End Location", ride.getEnd_location());
        System.out.println("End Location: " + ride.getEnd_location() + " - Test Passed");

        assertEquals("customer2", ride.getCustomer_username());
        System.out.println("Customer Username: " + ride.getCustomer_username() + " - Test Passed");

        assertEquals("rider2", ride.getRider_username());
        System.out.println("Rider Username: " + ride.getRider_username() + " - Test Passed");

        assertEquals(30.0, ride.getPrice());
        System.out.println("Price: " + ride.getPrice() + " - Test Passed");

        assertEquals(15.5, ride.getLengthOfRide());
        System.out.println("Length of Ride: " + ride.getLengthOfRide() + " - Test Passed");

        assertEquals("In Progress", ride.getRideStatus());
        System.out.println("Ride Status: " + ride.getRideStatus() + " - Test Passed");

        assertEquals("Bike", ride.getVehicleType());
        System.out.println("Vehicle Type: " + ride.getVehicleType() + " - Test Passed");

        assertEquals("XYZ-789", ride.getVehiclePlateNumber());
        System.out.println("Vehicle Plate Number: " + ride.getVehiclePlateNumber() + " - Test Passed");

        assertEquals("0987654321", ride.getMobile());
        System.out.println("Mobile: " + ride.getMobile() + " - Test Passed");

        assertEquals(startedAt, ride.getRideStartedAt());
        System.out.println("Ride Started At: " + ride.getRideStartedAt() + " - Test Passed");

        assertEquals(endedAt, ride.getRideEndedAt());
        System.out.println("Ride Ended At: " + ride.getRideEndedAt() + " - Test Passed");

        System.out.println("testRideSetters completed successfully.");
    }
}