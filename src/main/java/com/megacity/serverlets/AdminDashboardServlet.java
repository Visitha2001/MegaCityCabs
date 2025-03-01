package com.megacity.serverlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacity.models.Ride;
import com.megacity.models.User;
import com.megacity.services.DashboardService;
import com.megacity.services.impl.DashboardServiceImpl;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DashboardService dashboardService;
    
    public void init() {
        dashboardService = new DashboardServiceImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        try {
            // Get all dashboard statistics
            int totalRides = dashboardService.getTotalRidesCount();
            double totalIncome = dashboardService.getTotalIncome();
            int completedRides = dashboardService.getCompletedRidesCount();
            int requestedRides = dashboardService.getRequestedRidesCount();
            int assignedRides = dashboardService.getAssignedRidesCount();
            int totalRiders = dashboardService.getTotalRidersCount();
            int totalCustomers = dashboardService.getTotalCustomersCount();
            
//            Debugging
//            System.out.println("Total Rides: " + totalRides);
//            System.out.println("Total Income: " + totalIncome);
//            System.out.println("Completed Rides: " + completedRides);
//            System.out.println("Requested Rides: " + requestedRides);
//            System.out.println("Assigned Rides: " + assignedRides);
//            System.out.println("Total Riders: " + totalRiders);
//            System.out.println("Total Customers: " + totalCustomers);
            
            // Get recent rides (last 10)
            List<Ride> recentRides = dashboardService.getRecentRides(10);
            System.out.println("Recent Rides: " + recentRides.size());
            
            // Get rider statistics by vehicle type
            Map<String, Integer> riderStats = dashboardService.getRiderCountsByVehicleType();
            System.out.println("Rider Stats: " + riderStats);
            
            // Set attributes for JSP
            request.setAttribute("totalRides", totalRides);
            request.setAttribute("totalIncome", String.format("%.2f", totalIncome));
            request.setAttribute("completedRides", completedRides);
            request.setAttribute("requestedRides", requestedRides);
            request.setAttribute("assignedRides", assignedRides);
            request.setAttribute("totalRiders", totalRiders);
            request.setAttribute("totalCustomers", totalCustomers);
            request.setAttribute("recentRides", recentRides);
            request.setAttribute("riderStats", riderStats);
            
            // Forward to the dashboard JSP
            request.getRequestDispatcher("/role/admin/admin-index.jsp").forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/role/admin/admin-index.jsp").forward(request, response);
        }
    }
}