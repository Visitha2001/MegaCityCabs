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
        String action = req.getParameter("action");

        if ("assign_ride".equals(action)) {
            // Fetch all requested rides and forward to JSP
            List<Ride> requestedRides = rideService.getRequestedRides();
            req.setAttribute("requestedRides", requestedRides); // Set the list as a request attribute
            req.getRequestDispatcher("/assignRide.jsp").forward(req, resp); // Forward to JSP
        } else if ("requested_rides".equals(action)) {
            // Existing JSON response for API calls
            List<Ride> requestedRides = rideService.getRequestedRides();
            resp.setContentType("application/json");
            resp.getWriter().write(convertToJson(requestedRides));
        }
    }
    
    private String convertToJson(List<Ride> requestedRides) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        
        for (int i = 0; i < requestedRides.size(); i++) {
            Ride ride = requestedRides.get(i);
            jsonBuilder.append("{")
                .append("\"id\": ").append(ride.getId()).append(", ")
                .append("\"start_location\": \"").append(ride.getStart_location()).append("\", ")
                .append("\"end_location\": \"").append(ride.getEnd_location()).append("\", ")
                .append("\"customer_username\": \"").append(ride.getCustomer_username()).append("\", ")
                .append("\"rider_username\": \"").append(ride.getRider_username()).append("\", ")
                .append("\"price\": ").append(ride.getPrice()).append(", ")
                .append("\"length_of_ride\": ").append(ride.getLengthOfRide()).append(", ")
                .append("\"ride_status\": \"").append(ride.getRideStatus()).append("\", ")
                .append("\"vehicle_type\": \"").append(ride.getVehicleType()).append("\", ")
                .append("\"vehicle_plate_number\": \"").append(ride.getVehiclePlateNumber()).append("\", ")
                .append("\"ride_started_at\": \"").append(ride.getRideStartedAt()).append("\", ")
                .append("\"ride_ended_at\": \"").append(ride.getRideEndedAt()).append("\"")
                .append("}");
            
            if (i < requestedRides.size() - 1) {
                jsonBuilder.append(", ");
            }
        }

        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}