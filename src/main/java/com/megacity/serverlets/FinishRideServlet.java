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

@WebServlet("/finish")
public class FinishRideServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RideService rideService = new RideServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rideId = Integer.parseInt(request.getParameter("rideId"));

        // Update the ride status to "COMPLETED" and set the end time
        rideService.updateRideStatus(rideId, "COMPLETED", null, LocalDateTime.now());
        
        // Add success message to session
        request.getSession().setAttribute("successMessage", "Rlde Ended successful!");

        // Redirect back to the rides page
        response.sendRedirect(request.getContextPath() + "/rider-rides");
    }
}