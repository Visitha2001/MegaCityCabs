package com.megacity.serverlets;

import com.megacity.models.Ride;
import com.megacity.services.RideService;
import com.megacity.services.impl.RideServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RideServlet")
public class RideServlet extends HttpServlet {
    private final RideService rideService = new RideServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("book".equals(action)) {
            // Book a ride
            Ride ride = new Ride();
            ride.setStart_location(req.getParameter("start_location"));
            ride.setEnd_location(req.getParameter("end_location"));
            ride.setCustomer_username(req.getParameter("customer_username"));
            ride.setPrice(Double.parseDouble(req.getParameter("price")));
            ride.setLengthOfRide(Double.parseDouble(req.getParameter("length_of_ride")));
            ride.setRideStatus("REQUESTED");
            rideService.bookRide(ride);
            resp.getWriter().write("Ride booked successfully!");
        } else if ("assign".equals(action)) {
            // Assign a rider
            int rideId = Integer.parseInt(req.getParameter("ride_id"));
            String riderUsername = req.getParameter("rider_username");
            String vehicleType = req.getParameter("vehicle_type");
            String vehiclePlateNumber = req.getParameter("vehicle_plate_number");
            rideService.assignRider(rideId, riderUsername, vehicleType, vehiclePlateNumber);
            resp.getWriter().write("Rider assigned successfully!");
        } else if ("accept".equals(action)) {
            // Accept a ride
            int rideId = Integer.parseInt(req.getParameter("ride_id"));
            rideService.acceptRide(rideId);
            resp.getWriter().write("Ride accepted successfully!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("rider_rides".equals(action)) {
            // Fetch rides for rider
            String riderUsername = req.getParameter("rider_username");
            List<Ride> rides = rideService.getRidesForRider(riderUsername);
            resp.getWriter().write("Rides for rider: " + rides.toString());
        } else if ("customer_rides".equals(action)) {
            // Fetch rides for customer
            String customerUsername = req.getParameter("customer_username");
            List<Ride> rides = rideService.getRidesForCustomer(customerUsername);
            resp.getWriter().write("Rides for customer: " + rides.toString());
        }
    }
}