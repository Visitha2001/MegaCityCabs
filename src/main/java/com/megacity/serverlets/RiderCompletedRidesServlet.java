package com.megacity.serverlets;

import com.megacity.models.Ride;
import com.megacity.services.RideService;
import com.megacity.services.impl.RideServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/completed-rides-rider")
public class RiderCompletedRidesServlet extends HttpServlet {
    private RideService rideService = new RideServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("../../login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        List<Ride> completedRides = rideService.getCompletedRidesByRider(username);
		req.setAttribute("completedRides", completedRides);
		req.getRequestDispatcher("/role/rider/completedRides.jsp").forward(req, resp);
    }
}