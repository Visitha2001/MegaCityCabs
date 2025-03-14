package com.megacity.serverlets;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacity.models.User;
import com.megacity.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegistrationServlet registrationServlet;

    @Test
    void testDoPost_SuccessfulRegistration() throws ServletException, IOException {
        System.out.println("Running testDoPost_SuccessfulRegistration...");

        // Arrange
        System.out.println("Setting up mock request parameters...");
        when(request.getParameter("name")).thenReturn("John Doe22");
        when(request.getParameter("email")).thenReturn("john.doe123@example.com");
        when(request.getParameter("pass")).thenReturn("password123");
        when(request.getParameter("re_pass")).thenReturn("password123");
        when(request.getParameter("contact")).thenReturn("1223445566");
        when(request.getParameter("nic")).thenReturn("1223445566");
        when(request.getParameter("address")).thenReturn("123 Main St");
        when(request.getParameter("role")).thenReturn("customer");
        when(request.getSession()).thenReturn(session);

        System.out.println("Setting up mock response writer...");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        System.out.println("Creating User object and mocking UserService...");
        User user = new User("John Doe22", "john.doe123@example.com", "password123", "1223445566", "1223445566", "123 Main St", "customer", null, null);
        when(userService.registerUser(any(User.class))).thenReturn(true);

        // Act
        System.out.println("Calling doPost method...");
        registrationServlet.doPost(request, response);

        // Assert
        System.out.println("Verifying session attributes and redirect...");
        verify(session).setAttribute("username", "John Doe22");
        verify(session).setAttribute("email", "john.doe123@example.com");
        verify(session).setAttribute("role", "customer");
        verify(session).setAttribute("successMessage", "Registration successful!");
        verify(response).sendRedirect("role/client/index.jsp");

        System.out.println("testDoPost_SuccessfulRegistration completed successfully.");
    }

    @Test
    void testDoPost_PasswordsDoNotMatch() throws ServletException, IOException {
        System.out.println("Running testDoPost_PasswordsDoNotMatch...");

        // Arrange
        System.out.println("Setting up mock request parameters...");
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("john.doe@example.com");
        when(request.getParameter("pass")).thenReturn("password123");
        when(request.getParameter("re_pass")).thenReturn("differentpassword");
        when(request.getParameter("contact")).thenReturn("1234567890");
        when(request.getParameter("nic")).thenReturn("123456789V");
        when(request.getParameter("address")).thenReturn("123 Main St");
        when(request.getParameter("role")).thenReturn("client");
        when(request.getSession()).thenReturn(session);

        // Act
        System.out.println("Calling doPost method...");
        registrationServlet.doPost(request, response);

        // Assert
        System.out.println("Verifying error message and redirect...");
        verify(session).setAttribute("errorMessage", "Passwords do not match!");
        verify(response).sendRedirect("registration.jsp");

        System.out.println("testDoPost_PasswordsDoNotMatch completed successfully.");
    }

    @Test
    void testDoPost_MissingFields() throws ServletException, IOException {
        System.out.println("Running testDoPost_MissingFields...");

        // Arrange
        System.out.println("Setting up mock request parameters with missing fields...");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("pass")).thenReturn("");
        when(request.getParameter("re_pass")).thenReturn("");
        when(request.getParameter("contact")).thenReturn("");
        when(request.getParameter("nic")).thenReturn("");
        when(request.getParameter("address")).thenReturn("");
        when(request.getParameter("role")).thenReturn("");
        when(request.getSession()).thenReturn(session);

        // Act
        System.out.println("Calling doPost method...");
        registrationServlet.doPost(request, response);

        // Assert
        System.out.println("Verifying error message and redirect...");
        verify(session).setAttribute("errorMessage", "All fields are required!");
        verify(response).sendRedirect("registration.jsp");

        System.out.println("testDoPost_MissingFields completed successfully.");
    }
}