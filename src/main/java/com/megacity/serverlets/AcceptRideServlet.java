package com.megacity.serverlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.megacity.services.RideService;
import com.megacity.services.impl.RideServiceImpl;

@WebServlet("/accept")
public class AcceptRideServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RideService rideService = new RideServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rideId = Integer.parseInt(request.getParameter("rideId"));
        String action = request.getParameter("action");

        if ("accept".equals(action)) {
            // Update the ride status to "ACCEPTED" and set the start time
            rideService.updateRideStatus(rideId, "ACCEPTED", LocalDateTime.now(), null);
        } else if ("reject".equals(action)) {
            // Update the ride status to "REJECTED"
            rideService.updateRideStatus(rideId, "CANCELLED", null, null);
        }
        
        // Add success message to session
        request.getSession().setAttribute("successMessage", "Rlde Accepted successful!");

        // Redirect back to the rides page
        response.sendRedirect(request.getContextPath() + "/accepted");
    }
}