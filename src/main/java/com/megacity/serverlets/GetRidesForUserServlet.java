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
import java.util.List;

@WebServlet("/user-rides")
public class GetRidesForUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RideService rideService = new RideServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("../../login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");

        List<Ride> userRides = rideService.getRidesForUser(username);
        request.setAttribute("userRides", userRides);

        request.getRequestDispatcher("/role/client/viewRides.jsp").forward(request, response);
    }
}