package com.megacity.serverlets;

import com.megacity.services.RideService;
import com.megacity.services.impl.RideServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/rider-dashboard")
public class RiderDashboardServlet extends HttpServlet {
    private final RideService rideService = new RideServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Check if the user is logged in and is a rider
        if (session == null || session.getAttribute("username") == null || !"rider".equals(session.getAttribute("role"))) {
        	resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");

        // Fetch rider statistics
        int totalCompletedRides = rideService.getTotalCompletedRidesByRider(username);
        double totalIncome = rideService.getTotalIncomeByRider(username);
        int totalAssignedRides = rideService.getTotalAssignedRidesByRider(username);
        int totalAcceptedRides = rideService.getTotalAcceptedRidesByRider(username);

        // Set attributes for the JSP page
        req.setAttribute("totalCompletedRides", totalCompletedRides);
        req.setAttribute("totalIncome", totalIncome);
        req.setAttribute("totalAssignedRides", totalAssignedRides);
        req.setAttribute("totalAcceptedRides", totalAcceptedRides);

        // Forward to the rider dashboard JSP page
        req.getRequestDispatcher("/role/rider/rider-index.jsp").forward(req, resp);
    }
}