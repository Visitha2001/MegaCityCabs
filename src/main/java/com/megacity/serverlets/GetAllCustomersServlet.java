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
 * Servlet implementation class GetAllCustomersServlet
 */
@WebServlet("/customers")
public class GetAllCustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all customers from the service
        List<User> customers = userService.getAllCustomers();

        // Set the customers as an attribute in the request
        request.setAttribute("customers", customers);

        // Forward to the JSP page
        request.getRequestDispatcher("/role/admin/allCustomers.jsp").forward(request, response);
    }
}
