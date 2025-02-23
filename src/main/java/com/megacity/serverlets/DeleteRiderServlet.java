package com.megacity.serverlets;

import com.megacity.services.UserService;
import com.megacity.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteRider")
public class DeleteRiderServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        boolean isDeleted = userService.deleteRider(username);

        if (isDeleted) {
            response.sendRedirect("riders");
        } else {
            request.setAttribute("error", "Failed to delete rider.");
            request.getRequestDispatcher("/role/admin/allriders.jsp").forward(request, response);
        }
    }
}