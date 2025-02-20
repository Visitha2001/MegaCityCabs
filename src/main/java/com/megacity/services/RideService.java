package com.megacity.services;

import com.megacity.models.Ride;

import java.time.LocalDateTime;
import java.util.List;

public interface RideService {
    void bookRide(Ride ride);
    List<Ride> getRequestedRides();
    void assignRider(int rideId, String riderUsername, String plateNumber, String mobile);
    List<Ride> getAssignedRides();
    List<Ride> getRidesForUser(String username);
    List<Ride> getRidesForRiders(String username);
    List<Ride> getAcceptedRidesForRiders(String username);
    void updateRideStatus(int rideId, String status, LocalDateTime rideStartedAt, LocalDateTime rideEndedAt);
}