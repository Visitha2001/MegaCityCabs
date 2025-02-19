package com.megacity.serverlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.megacity.models.User;
import com.megacity.services.impl.UserServiceImpl;

@WebServlet("/user")
public class userServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleType = request.getParameter("vehicleType");
        if (vehicleType != null) {
            List<User> riders = userService.getRidersByVehicleType(vehicleType);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            StringBuilder json = new StringBuilder("[");
            for (User rider : riders) {
                json.append("{")
                    .append("\"username\":\"").append(rider.getUsername()).append("\",")
                    .append("\"vehicleType\":\"").append(rider.getVehicleType()).append("\",")
                    .append("\"plateNumber\":\"").append(rider.getPlateNumber()).append("\",")
                    .append("\"mobile\":\"").append(rider.getMobile()).append("\"")
                    .append("},");
            }
            if (riders.size() > 0) {
                json.deleteCharAt(json.length() - 1);
            }
            json.append("]");
            response.getWriter().write(json.toString());
        }
    }
}