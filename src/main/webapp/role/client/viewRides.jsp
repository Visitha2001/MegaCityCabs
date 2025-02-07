<%@ page import="com.megacity.services.RideService" %>
<%@ page import="com.megacity.services.impl.RideServiceImpl" %>
<%@ page import="com.megacity.models.Ride" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View My Rides</title>
</head>
<body>
    <h2>My Rides</h2>
    <%
        String customerUsername = request.getParameter("customer_username");
        RideService rideService = new RideServiceImpl();
        List<Ride> rides = rideService.getRidesForCustomer(customerUsername);
    %>
    <table border="1">
        <tr>
            <th>Start Location</th>
            <th>End Location</th>
            <th>Price</th>
            <th>Length of Ride</th>
            <th>Status</th>
            <th>Rider</th>
        </tr>
        <% for (Ride ride : rides) { %>
        <tr>
            <td><%= ride.getStart_location() %></td>
            <td><%= ride.getEnd_location() %></td>
            <td><%= ride.getPrice() %></td>
            <td><%= ride.getLengthOfRide() %></td>
            <td><%= ride.getRideStatus() %></td>
            <td><%= ride.getRider_username() != null ? ride.getRider_username() : "Not Assigned" %></td>
        </tr>
        <% } %>
    </table>
    <br>
    <a href="customer/bookRide.jsp">Book Another Ride</a>
</body>
</html>