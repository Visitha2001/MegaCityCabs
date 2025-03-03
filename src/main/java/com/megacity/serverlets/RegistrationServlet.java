package com.megacity.serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.megacity.models.User;
import com.megacity.services.UserService;
import com.megacity.services.impl.UserServiceImpl;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data
        String username = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String repassword = request.getParameter("re_pass");
        String mobile = request.getParameter("contact");
        String nic = request.getParameter("nic");
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        String vehicleType = request.getParameter("vehicleType");
        String plateNumber = request.getParameter("plateNumber");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

     // Input validation
        if (username == null || username.isEmpty() || email == null || email.isEmpty() ||
            password == null || password.isEmpty() || repassword == null || repassword.isEmpty() ||
            mobile == null || mobile.isEmpty() || nic == null || nic.isEmpty() || address == null || address.isEmpty() ||
            role == null || role.isEmpty()) {
            
            // Set error message in session
            request.getSession().setAttribute("errorMessage", "All fields are required!");
            response.sendRedirect("registration.jsp"); // Redirect back to the registration page
            return;
        }

        if (!password.equals(repassword)) {
            // Set error message in session
            request.getSession().setAttribute("errorMessage", "Passwords do not match!");
            response.sendRedirect("registration.jsp"); // Redirect back to the registration page
            return;
        }

        // Optional validation for rider-specific fields
        if ("rider".equals(role)) {
            if (vehicleType == null || vehicleType.isEmpty() || plateNumber == null || plateNumber.isEmpty()) {
                // Set error message in session
                request.getSession().setAttribute("errorMessage", "Vehicle type and plate number are required for riders!");
                response.sendRedirect("registration.jsp"); // Redirect back to the registration page
                return;
            }
        }

        // Create User object
        User user = new User(username, email, password, mobile, nic, address, role, vehicleType, plateNumber);

        // Use UserService to register the user
        UserService userService = new UserServiceImpl();
        boolean registrationSuccess = userService.registerUser(user);

        if (registrationSuccess) {
            // Set user in session
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("role", role);
            
            // Add success message to session
            request.getSession().setAttribute("successMessage", "Registration successful!");

            // Redirect based on role
            if ("admin".equals(user.getRole())) {
            	response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else if ("rider".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/rider-dashboard");
            } else {
                response.sendRedirect("role/client/index.jsp");
            }
        } else {
        	// Set error message in session
            request.getSession().setAttribute("errorMessage", "Registration failed. Please try again.");
            
            // Redirect back to the registration page
            response.sendRedirect("registration.jsp");
        }
    }
}