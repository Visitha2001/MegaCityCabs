package com.megacity.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.megacity.models.Ride;

public interface DashboardService {
    int getTotalRidesCount() throws SQLException;
    double getTotalIncome() throws SQLException;
    int getCompletedRidesCount() throws SQLException;
    int getRequestedRidesCount() throws SQLException;
    int getAssignedRidesCount() throws SQLException;
    List<Ride> getRecentRides(int limit) throws SQLException;
    Map<String, Integer> getRiderCountsByVehicleType() throws SQLException;
    int getTotalRidersCount() throws SQLException;
    int getTotalCustomersCount() throws SQLException;
}