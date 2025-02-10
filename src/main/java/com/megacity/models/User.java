package com.megacity.models;

public class User {
    private String username;
    private String email;
    private String password;
    private String mobile;
    private String nic;
    private String address;
    private String role;
    private String vehicleType;
    private String plateNumber;

    // Constructor
    public User(String username, String email, String password, String mobile, String nic, String address, String role, String vehicleType, String plateNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.nic = nic;
        this.address = address;
        this.role = role;
        this.vehicleType = vehicleType; 
        this.plateNumber = plateNumber;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getVehicleType() { return vehicleType; } // Corrected spelling
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; } // Corrected spelling

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
}