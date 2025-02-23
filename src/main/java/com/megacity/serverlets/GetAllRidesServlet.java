package com.megacity.serverlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.models.User;
import com.megacity.services.UserService;
import com.megacity.services.impl.UserServiceImpl;

/**
 * Servlet implementation class GetAllRidesServlet
 */
@WebServlet("/riders")
public class GetAllRidesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all riders from the service
        List<User> riders = userService.getAllRiders();

        // Set the riders as an attribute in the request
        request.setAttribute("riders", riders);

        // Forward to the JSP page
        request.getRequestDispatcher("/role/admin/allRiders.jsp").forward(request, response);
    }
}