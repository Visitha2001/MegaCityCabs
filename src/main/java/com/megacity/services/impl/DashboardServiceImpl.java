package com.megacity.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.megacity.dao.RideDao;
import com.megacity.dao.UserDao;
import com.megacity.models.Ride;
import com.megacity.services.DashboardService;

public class DashboardServiceImpl implements DashboardService {
    private RideDao rideDao;
    private UserDao userDao;
    
    public DashboardServiceImpl() {
        this.rideDao = new RideDao();
        this.userDao = new UserDao();
    }

    @Override
    public int getTotalRidesCount() throws SQLException {
        return rideDao.getTotalRidesCount();
    }

    @Override
    public double getTotalIncome() throws SQLException {
        return rideDao.getTotalIncome();
    }

    @Override
    public int getCompletedRidesCount() throws SQLException {
        return rideDao.getCompletedRidesCount();
    }

    @Override
    public int getRequestedRidesCount() throws SQLException {
        return rideDao.getRequestedRidesCount();
    }

    @Override
    public int getAssignedRidesCount() throws SQLException {
        return rideDao.getAssignedRidesCount();
    }

    @Override
    public List<Ride> getRecentRides(int limit) throws SQLException {
        return rideDao.getRecentRides(limit);
    }

    @Override
    public Map<String, Integer> getRiderCountsByVehicleType() throws SQLException {
        return userDao.getRiderCountsByVehicleType();
    }

    @Override
    public int getTotalRidersCount() throws SQLException {
        return userDao.getTotalRidersCount();
    }

    @Override
    public int getTotalCustomersCount() throws SQLException {
        return userDao.getTotalCustomersCount();
    }
}