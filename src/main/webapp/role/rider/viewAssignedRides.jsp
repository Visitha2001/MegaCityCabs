<%@ page import="com.megacity.services.RideService" %>
<%@ page import="com.megacity.services.impl.RideServiceImpl" %>
<%@ page import="com.megacity.models.Ride" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Assigned Rides</title>
</head>
<body>
    <h2>My Assigned Rides</h2>
    <%
        String riderUsername = request.getParameter("rider_username");
        RideService rideService = new RideServiceImpl();
        List<Ride> rides = rideService.getRidesForRider(riderUsername);
    %>
    <table border="1">
        <tr>
            <th>Start Location</th>
            <th>End Location</th>
            <th>Price</th>
            <th>Length of Ride</th>
            <th>Status</th>
        </tr>
        <% for (Ride ride : rides) { %>
        <tr>
            <td><%= ride.getStart_location() %></td>
            <td><%= ride.getEnd_location() %></td>
            <td><%= ride.getPrice() %></td>
            <td><%= ride.getLengthOfRide() %></td>
            <td><%= ride.getRideStatus() %></td>
        </tr>
        <% } %>
    </table>
    <br>
    <a href="rider/acceptRide.jsp">Accept a Ride</a>
</body>
</html>