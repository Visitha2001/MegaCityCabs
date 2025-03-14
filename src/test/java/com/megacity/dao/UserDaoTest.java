package com.megacity.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.megacity.models.User;

class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();
        System.out.println("UserDaoTest setup complete.");
    }

    @Test
    void testRegisterUser() {
        System.out.println("Running testRegisterUser...");

        User user = new User(
        	    "testuser",               // username
        	    "testuser@example.com",   // email
        	    "password",               // password
        	    "1234567890",             // mobile
        	    "123456789V",             // NIC
        	    "123 Test St",            // address
        	    "customer",               // role
        	    "Car",                    // vehicleType
        	    "XYZ123"                  // plateNumber
        	);

        boolean result = userDao.registerUser(user);
        assertTrue(result);
        System.out.println("User registered successfully.");

        System.out.println("testRegisterUser completed successfully.");
    }

    @Test
    void testAuthenticateUser() {
        System.out.println("Running testAuthenticateUser...");

        User user = userDao.authenticateUser("testuser", "password");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        System.out.println("User authenticated successfully.");

        System.out.println("testAuthenticateUser completed successfully.");
    }

    @Test
    void testGetRidersByVehicleType() {
        System.out.println("Running testGetRidersByVehicleType...");

        List<User> riders = userDao.getRidersByVehicleType("Car");
        assertNotNull(riders);
        assertFalse(riders.isEmpty());
        System.out.println("Retrieved " + riders.size() + " riders with vehicle type 'Car'.");

        System.out.println("testGetRidersByVehicleType completed successfully.");
    }

    @Test
    void testGetAllRiders() {
        System.out.println("Running testGetAllRiders...");

        List<User> riders = userDao.getAllRiders();
        assertNotNull(riders);
        assertFalse(riders.isEmpty());
        System.out.println("Retrieved " + riders.size() + " riders.");

        System.out.println("testGetAllRiders completed successfully.");
    }

    @Test
    void testGetAllCustomers() {
        System.out.println("Running testGetAllCustomers...");

        List<User> customers = userDao.getAllCustomers();
        assertNotNull(customers);
        assertFalse(customers.isEmpty());
        System.out.println("Retrieved " + customers.size() + " customers.");

        System.out.println("testGetAllCustomers completed successfully.");
    }
}