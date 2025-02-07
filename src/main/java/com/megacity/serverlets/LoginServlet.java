package com.megacity.serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.megacity.models.User;
import com.megacity.services.UserService;
import com.megacity.services.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String usernameOrEmail = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Input validation
        if (usernameOrEmail == null || usernameOrEmail.isEmpty() || password == null || password.isEmpty()) {
            out.println("<h3 style='color:red'>All fields are required!</h3>");
            return;
        }

        // Use UserService to authenticate the user
        UserService userService = new UserServiceImpl();
        User user = userService.authenticateUser(usernameOrEmail, password);

        if (user != null) {
            // Set user in session
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            // Redirect based on role
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("role/admin/admin-index.jsp");
            } else if ("rider".equals(user.getRole())) {
                response.sendRedirect("role/rider/rider-index.jsp");
            } else {
                response.sendRedirect("role/client/index.jsp");
            }
        } else {
            out.println("<h3 style='color:red'>Invalid username/email or password!</h3>");
        }
    }
}