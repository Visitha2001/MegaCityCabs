package com.megacity.serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.megacity.dao.UserDao;
import com.megacity.models.User;

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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Input validation
        if (username == null || username.isEmpty() || email == null || email.isEmpty() ||
            password == null || password.isEmpty() || repassword == null || repassword.isEmpty() ||
            mobile == null || mobile.isEmpty()) {
            out.println("<h3 style='color:red'>All fields are required!</h3>");
            return;
        }

        if (!password.equals(repassword)) {
            out.println("<h3 style='color:red'>Passwords do not match!</h3>");
            return;
        }

        // Create a User object
        User user = new User(username, email, password, mobile);

        // Use UserDao to register the user
        UserDao userDao = new UserDao();
        boolean registrationSuccess = userDao.registerUser(user);

        if (registrationSuccess) {
            // Set user in session
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("email", email);

            // Redirect to index page
            response.sendRedirect("index.jsp"); // Redirect to index.jsp or desired page
        } else {
            out.println("<h3 style='color:red'>Registration failed. Please try again.</h3>");
        }
    }
}