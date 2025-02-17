package com.megacity.services;

import com.megacity.models.Ride;

import java.util.List;

public interface RideService {
    void bookRide(Ride ride);
    List<Ride> getRequestedRides();
    void assignRider(int rideId, String riderUsername, String plateNumber);
    List<Ride> getAssignedRides();
    List<Ride> getRidesForUser(String username);
}