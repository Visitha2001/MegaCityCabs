package com.megacity.services;

import com.megacity.models.Ride;

import java.util.List;

public interface RideService {
    void bookRide(Ride ride);
    void assignRider(int rideId, String riderUsername, String vehicleType, String vehiclePlateNumber);
    void acceptRide(int rideId);
    List<Ride> getRidesForRider(String riderUsername);
    List<Ride> getRidesForCustomer(String customerUsername);
}