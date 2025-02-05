package com.megacity.serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.megacity.dao.UserDao;

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

        // Use UserDao to authenticate the user
        UserDao userDao = new UserDao();
        boolean isAuthenticated = userDao.authenticateUser(usernameOrEmail, password);

        if (isAuthenticated) {
            // Set user in session
            HttpSession session = request.getSession();
            session.setAttribute("username", usernameOrEmail);
            // Redirect to index page
            response.sendRedirect("index.jsp"); // Redirect to index.jsp or desired page
        } else {
            out.println("<h3 style='color:red'>Invalid username/email or password!</h3>");
        }
    }
}