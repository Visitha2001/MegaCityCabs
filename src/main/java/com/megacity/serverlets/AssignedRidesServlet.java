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

@WebServlet("/assigned")
public class AssignedRidesServlet extends HttpServlet {
    private RideService rideService = new RideServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Ride> assignedRides = rideService.getAssignedRides();
            req.setAttribute("assignedRides", assignedRides);
            req.getRequestDispatcher("role/admin/assignedRides.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching assigned rides");
        }
    }
}