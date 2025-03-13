-- Create the database
CREATE DATABASE IF NOT EXISTS signup;
USE signup;

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    mobile VARCHAR(50) NOT NULL,
    NIC VARCHAR(15) NOT NULL UNIQUE,
    address VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    vehicleType VARCHAR(100),
    plateNumber VARCHAR(100)
);

-- Create the rides table
CREATE TABLE IF NOT EXISTS rides (
    id INT AUTO_INCREMENT PRIMARY KEY,
    start_location VARCHAR(255) NOT NULL,
    end_location VARCHAR(255) NOT NULL,
    customer_username VARCHAR(255) NOT NULL,
    rider_username VARCHAR(255),
    price DECIMAL(10, 2),
    length_of_ride DOUBLE,
    ride_status ENUM('REQUESTED', 'ASSIGNED', 'ACCEPTED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED') DEFAULT 'REQUESTED',
    vehicle_type ENUM('CAR', 'SUV', 'BIKE', 'VAN'),
    vehicle_plate_number VARCHAR(50),
    mobile VARCHAR(50),
    ride_started_at DATETIME,
    ride_ended_at DATETIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert the admin user
INSERT INTO users (username, email, password, mobile, NIC, address, role)
VALUES ('admin', 'admin@example.com', 'admin123', '1234567890', '123456789', 'Admin Address', 'admin');
