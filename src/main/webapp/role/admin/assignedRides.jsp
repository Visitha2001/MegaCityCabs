<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.Ride" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Assigned Rides</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/admin/css/allrides.css">
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("../../login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Retrieve the list of rides and riders from the request attributes
        List<Ride> assignedRides = (List<Ride>) request.getAttribute("assignedRides");
    %>
    <!-- Include the header -->
    <jsp:include page="header.jsp" />
    
    <div class="container_big">
        <h1>Assigned Rides</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Start Location</th>
                <th>End Location</th>
                <th>Customer Username</th>
                <th>Price</th>
                <th>Length of Ride</th>
                <th>Req. Vehicle</th>
                <th>Status</th>
                <th>Rider Username</th>
                <th>Vehicle Plate No.</th>
                <th>Mobile No.</th>
            </tr>
            <%
                if (assignedRides != null && !assignedRides.isEmpty()) {
                    for (Ride ride : assignedRides) {
            %>
            <tr>
                <td><%= ride.getId() %></td>
                <td><%= ride.getStart_location() %></td>
                <td><%= ride.getEnd_location() %></td>
                <td><%= ride.getCustomer_username() %></td>
                <td><%= ride.getPrice() %></td>
                <td><%= ride.getLengthOfRide() %></td>
                <td><%= ride.getVehicleType() %></td>
                <td><%= ride.getRideStatus() %></td>
                <td><%= ride.getRider_username() %></td>
                <td><%= ride.getVehiclePlateNumber() %></td>
                <td><%= ride.getMobile() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="9">No requested rides available.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>