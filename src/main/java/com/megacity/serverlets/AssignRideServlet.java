package com.megacity.serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.megacity.services.RideService;
import com.megacity.services.impl.RideServiceImpl;

@WebServlet("/assignRide")
public class AssignRideServlet extends HttpServlet {
    private final RideService rideService = new RideServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the ride ID, rider username, and plate number from the form
        int rideId = Integer.parseInt(req.getParameter("rideId"));
        String riderUsername = req.getParameter("riderUsername");
        String plateNumber = req.getParameter("plateNumber");
        String mobile = req.getParameter("mobile");
        
//        // Log the received parameters
//        System.out.println("Ride ID: " + rideId);
//        System.out.println("Rider Username: " + riderUsername);
//        System.out.println("Plate Number: " + plateNumber);
//        System.out.println("Mobile: " + mobile);

        // Assign the rider to the ride
        rideService.assignRider(rideId, riderUsername, plateNumber , mobile);
        
        // Add success message to session
        req.getSession().setAttribute("successMessage", "Rlder assigned successful!");

        // Redirect back to the allRides.jsp page or send a success message
        resp.sendRedirect(req.getContextPath() + "/ride");
    }
}