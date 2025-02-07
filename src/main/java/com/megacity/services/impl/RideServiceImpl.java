package com.megacity.services.impl;

import com.megacity.dao.RideDao;
import com.megacity.models.Ride;
import com.megacity.services.RideService;

import java.sql.SQLException;
import java.util.List;

public class RideServiceImpl implements RideService {
    private final RideDao rideDao = new RideDao();

    @Override
    public void bookRide(Ride ride) {
        try {
            rideDao.bookRide(ride);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to book ride", e);
        }
    }

    @Override
    public void assignRider(int rideId, String riderUsername, String vehicleType, String vehiclePlateNumber) {
        try {
            rideDao.assignRider(rideId, riderUsername, vehicleType, vehiclePlateNumber);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to assign rider", e);
        }
    }

    @Override
    public void acceptRide(int rideId) {
        try {
            rideDao.acceptRide(rideId);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to accept ride", e);
        }
    }

    @Override
    public List<Ride> getRidesForRider(String riderUsername) {
        try {
            return rideDao.getRidesForRider(riderUsername);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch rides for rider", e);
        }
    }

    @Override
    public List<Ride> getRidesForCustomer(String customerUsername) {
        try {
            return rideDao.getRidesForCustomer(customerUsername);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch rides for customer", e);
        }
    }
}