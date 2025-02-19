package com.megacity.models;

import java.time.LocalDateTime;
import java.time.Duration;

public class Ride {
    private int id; // Add this field for the ride ID
    private String start_location;
    private String end_location;
    private String customer_username;
    private String rider_username;
    private double price;
    private double lengthOfRide;
    private String rideStatus;
    private String vehicleType;
    private String vehiclePlateNumber;
    private String mobile;
    private LocalDateTime rideStartedAt;
    private LocalDateTime rideEndedAt;

    // Default Constructor
    public Ride() {}

    // Constructor with all fields including ID
    public Ride(int id, String start_location, String end_location, String customer_username, String rider_username,
                double price, double lengthOfRide, String rideStatus, String vehicleType, String vehiclePlateNumber,
                String mobile,
                LocalDateTime rideStartedAt, LocalDateTime rideEndedAt) {
        this.id = id;
        this.start_location = start_location;
        this.end_location = end_location;
        this.customer_username = customer_username;
        this.rider_username = rider_username;
        this.price = price;
        this.lengthOfRide = lengthOfRide;
        this.rideStatus = rideStatus;
        this.vehicleType = vehicleType;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.mobile = mobile;
        this.rideStartedAt = rideStartedAt;
        this.rideEndedAt = rideEndedAt;
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and Setters
    public String getStart_location() {
        return start_location;
    }

    public void setStart_location(String start_location) {
        this.start_location = start_location;
    }

    public String getEnd_location() {
        return end_location;
    }

    public void setEnd_location(String end_location) {
        this.end_location = end_location;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public String getRider_username() {
        return rider_username;
    }

    public void setRider_username(String rider_username) {
        this.rider_username = rider_username;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLengthOfRide() {
        return lengthOfRide;
    }

    public void setLengthOfRide(double lengthOfRide) {
        this.lengthOfRide = lengthOfRide;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public LocalDateTime getRideStartedAt() {
        return rideStartedAt;
    }

    public void setRideStartedAt(LocalDateTime rideStartedAt) {
        this.rideStartedAt = rideStartedAt;
    }

    public LocalDateTime getRideEndedAt() {
        return rideEndedAt;
    }

    public void setRideEndedAt(LocalDateTime rideEndedAt) {
        this.rideEndedAt = rideEndedAt;
    }
    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}