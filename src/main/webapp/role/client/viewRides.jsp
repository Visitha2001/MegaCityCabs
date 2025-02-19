<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.Ride" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Rides</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/client/css/allrides.css">
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("${pageContext.request.contextPath}/login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Retrieve the list of rides for the user from the request attributes
        List<Ride> userRides = (List<Ride>) request.getAttribute("userRides");
    %>
    <!-- Include the header -->
    <jsp:include page="header.jsp" />
    
    <h1>My Booked Rides</h1>
    <h2>Rides For <%= username %></h2>
    <hr/>
    <div class="container_big">

        <% if (userRides != null && !userRides.isEmpty()) { %>
        <!-- Displaying labels at the top of the container -->
        <div class="ride-labels">
            <span>ID</span>
            <span>Start Location</span>
            <span>End Location</span>
            <span>Price</span>
            <span>Length of Ride</span>
            <span>Req. Vehicle</span>
            <span>Status</span>
            <span>Cus. Username</span>
            <span>Rider Username</span>
            <span>Vehicle Plate No.</span>
        </div>

        <% for (Ride ride : userRides) { %>
        <div class="ride-card">
            <div class="ride-info">
                <div class="ride-field">
                    <span class="field-value"><%= ride.getId() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getStart_location() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getEnd_location() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getPrice() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getLengthOfRide() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getVehicleType() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getRideStatus() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getCustomer_username() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getRider_username() %></span>
                </div>
                <div class="ride-field">
                    <span class="field-value"><%= ride.getVehiclePlateNumber() %></span>
                </div>
                <% if (ride.getMobile() != null && !ride.getMobile().isEmpty()) { %>
				    <div class="ride-field">
				        <span>Rider Mobile : </span>
				        <span class="field-value"><%= ride.getMobile() %></span>
				    </div>
				<% } %>
				
				<% if (ride.getRideStartedAt() != null) { %>
				    <div class="ride-field">
				        <span>Ride Started At : </span>
				        <span class="field-value">
				            <%= ride.getRideStartedAt().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %>
				        </span>
				    </div>
				<% } %>
            </div>
            <hr/>
            <div class="ride-action">
                <% if ("REQUESTED".equals(ride.getRideStatus()) || "ASSIGNED".equals(ride.getRideStatus())) { %>
                <form action="cancel-ride" method="post">
                    <input type="hidden" name="rideId" value="<%= ride.getId() %>">
                    <button type="submit" class="btn-bsic">Cancel</button>
                </form>
                <% } else { %>
                <span class="cancel-message">Cannot cancel ride after accepted by rider</span>
                <% } %>
            </div>
        </div>
        <% } %>
        <% } else { %>
        <div class="no-rides">
            No rides available.
        </div>
        <% } %>
    </div>
</body>
</html>
