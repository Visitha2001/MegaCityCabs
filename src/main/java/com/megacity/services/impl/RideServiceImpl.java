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
    public List<Ride> getRequestedRides() {
        try {
            return rideDao.getRequestedRides();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch requested rides", e);
        }
    }
    
    @Override
    public void assignRider(int rideId, String riderUsername, String plateNumber) {
        try {
            rideDao.assignRider(rideId, riderUsername, plateNumber);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to assign rider", e);
        }
    }
    
    @Override
    public List<Ride> getAssignedRides() {
        try {
            return rideDao.getAssignedRides();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch assigned rides", e);
        }
    }
}