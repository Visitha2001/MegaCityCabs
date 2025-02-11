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
            ride.setVehicleType(req.getParameter("vehicleType"));
            rideService.bookRide(ride);
            resp.getWriter().write("Ride booked successfully!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetch all requested rides
        List<Ride> requestedRides = rideService.getRequestedRides();
        
        // Set the list of requested rides as a request attribute
        req.setAttribute("requestedRides", requestedRides);
        
        // Forward the request to the allRides.jsp page
        req.getRequestDispatcher("role/admin/allRides.jsp").forward(req, resp);
    }
}