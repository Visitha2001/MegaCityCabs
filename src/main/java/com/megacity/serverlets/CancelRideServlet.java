package com.megacity.serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.services.RideService;
import com.megacity.services.impl.RideServiceImpl;

@WebServlet("/cancel-ride")
public class CancelRideServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RideService rideService = new RideServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rideId = Integer.parseInt(request.getParameter("rideId"));
        String action = request.getParameter("action");

        // Update the ride status to "CANCELLED" and pass null for start and end times
        rideService.updateRideStatus(rideId, "CANCELLED", null, null);


        // Add success message to session
        request.getSession().setAttribute("successMessage", "Rlde cancelled successful!");
        
        // Redirect back to the rides page
        response.sendRedirect(request.getContextPath() + "/user-rides");
    }
}