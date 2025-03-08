package com.megacity.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        System.out.println("Running testUserConstructorAndGetters...");

        User user = new User("user1", "user1@example.com", "password123", "1234567890", "123456789", "123 Main St", "Customer", "Car", "ABC-123");

        System.out.println("Testing User object created with constructor...");

        assertEquals("user1", user.getUsername());
        System.out.println("Username: " + user.getUsername() + " - Test Passed");

        assertEquals("user1@example.com", user.getEmail());
        System.out.println("Email: " + user.getEmail() + " - Test Passed");

        assertEquals("password123", user.getPassword());
        System.out.println("Password: " + user.getPassword() + " - Test Passed");

        assertEquals("1234567890", user.getMobile());
        System.out.println("Mobile: " + user.getMobile() + " - Test Passed");

        assertEquals("123456789", user.getNic());
        System.out.println("NIC: " + user.getNic() + " - Test Passed");

        assertEquals("123 Main St", user.getAddress());
        System.out.println("Address: " + user.getAddress() + " - Test Passed");

        assertEquals("Customer", user.getRole());
        System.out.println("Role: " + user.getRole() + " - Test Passed");

        assertEquals("Car", user.getVehicleType());
        System.out.println("Vehicle Type: " + user.getVehicleType() + " - Test Passed");

        assertEquals("ABC-123", user.getPlateNumber());
        System.out.println("Plate Number: " + user.getPlateNumber() + " - Test Passed");

        System.out.println("testUserConstructorAndGetters completed successfully.");
    }

    @Test
    void testUserSetters() {
        System.out.println("Running testUserSetters...");

        User user = new User("user1", "user1@example.com", "password123", "1234567890", "123456789", "123 Main St", "Customer", "Car", "ABC-123");

        System.out.println("Setting new values for User object...");

        user.setUsername("user2");
        user.setEmail("user2@example.com");
        user.setPassword("newpassword");
        user.setMobile("0987654321");
        user.setNic("987654321");
        user.setAddress("456 Elm St");
        user.setRole("Rider");
        user.setVehicleType("Bike");
        user.setPlateNumber("XYZ-789");

        System.out.println("Testing updated User object...");

        assertEquals("user2", user.getUsername());
        System.out.println("Username: " + user.getUsername() + " - Test Passed");

        assertEquals("user2@example.com", user.getEmail());
        System.out.println("Email: " + user.getEmail() + " - Test Passed");

        assertEquals("newpassword", user.getPassword());
        System.out.println("Password: " + user.getPassword() + " - Test Passed");

        assertEquals("0987654321", user.getMobile());
        System.out.println("Mobile: " + user.getMobile() + " - Test Passed");

        assertEquals("987654321", user.getNic());
        System.out.println("NIC: " + user.getNic() + " - Test Passed");

        assertEquals("456 Elm St", user.getAddress());
        System.out.println("Address: " + user.getAddress() + " - Test Passed");

        assertEquals("Rider", user.getRole());
        System.out.println("Role: " + user.getRole() + " - Test Passed");

        assertEquals("Bike", user.getVehicleType());
        System.out.println("Vehicle Type: " + user.getVehicleType() + " - Test Passed");

        assertEquals("XYZ-789", user.getPlateNumber());
        System.out.println("Plate Number: " + user.getPlateNumber() + " - Test Passed");

        System.out.println("testUserSetters completed successfully.");
    }
}