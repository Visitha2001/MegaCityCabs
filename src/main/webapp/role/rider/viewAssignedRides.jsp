<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.Ride" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Rides</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/rider/css/allrides.css">
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("../../login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Retrieve the list of rides for the user from the request attributes
        List<Ride> riderRides = (List<Ride>) request.getAttribute("riderRides");
    %>
    <!-- Include the header -->
    <jsp:include page="header.jsp" />
    
    <div class="container_big">
        <h1>My Rides</h1>
		<h2>Rides For <%= username %></h2>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Start Location</th>
                <th>End Location</th>
                <th>Price</th>
                <th>Length of Ride</th>
                <th>Req. Vehicle</th>
                <th>Status</th>
                <th>Cus. Username</th>
                <th>Rider Username</th>
                <th>Vehicle Plate No.</th>
                <th>Action</th>
            </tr>
            <%
                if (riderRides != null && !riderRides.isEmpty()) {
                    for (Ride ride : riderRides) {
            %>
            <tr>
                <td><%= ride.getId() %></td>
                <td><%= ride.getStart_location() %></td>
                <td><%= ride.getEnd_location() %></td>
                <td><%= ride.getPrice() %></td>
                <td><%= ride.getLengthOfRide() %></td>
                <td><%= ride.getVehicleType() %></td>
                <td><%= ride.getRideStatus() %></td>
                <td><%= ride.getCustomer_username() %></td>
                <td><%= ride.getRider_username() %></td>
                <td><%= ride.getVehiclePlateNumber() %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/accept" method="post" style="display:inline;">
                        <input type="hidden" name="rideId" value="<%= ride.getId() %>">
                        <input type="hidden" name="action" value="accept">
                        <button type="submit" class="btn-bsic">Accept Ride</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/accept" method="post" style="display:inline;">
                        <input type="hidden" name="rideId" value="<%= ride.getId() %>">
                        <input type="hidden" name="action" value="reject">
                        <button type="submit" class="btn-bsic">Reject Ride</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="11">No rides available.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>