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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Input validation
        if (username == null || username.isEmpty() || email == null || email.isEmpty() ||
            password == null || password.isEmpty() || repassword == null || repassword.isEmpty() ||
            mobile == null || mobile.isEmpty() || nic == null || nic.isEmpty() || address == null || address.isEmpty()) {
            out.println("<h3 style='color:red'>All fields are required!</h3>");
            return;
        }

        if (!password.equals(repassword)) {
            out.println("<h3 style='color:red'>Passwords do not match!</h3>");
            return;
        }

        // Create User object
        User user = new User(username, email, password, mobile, nic, address, role);

        // Use UserService to register the user
        UserService userService = new UserServiceImpl();
        boolean registrationSuccess = userService.registerUser(user);

        if (registrationSuccess) {
            // Set user in session
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("role", role);
            
            // Redirect based on role
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin-index.jsp");
            } else if ("rider".equals(user.getRole())) {
                response.sendRedirect("rider-index.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            out.println("<h3 style='color:red'>Registration failed. Please try again.</h3>");
        }
    }
}