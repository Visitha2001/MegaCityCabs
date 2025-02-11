<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.Ride" %>

<!DOCTYPE html>
<html>
<head>
    <title>Assign Ride</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/admin/css/allrides.css">
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("../../login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
    %>

    <!-- Include the header -->
    <jsp:include page="header.jsp" />
    
    <div class="container_big">
    	<h1>Assign Ride</h1>
	    <table border="1">
	        <tr>
	            <th>ID</th>
	            <th>Start Location</th>
	            <th>End Location</th>
	            <th>Customer Username</th>
	            <th>Price</th>
	            <th>Length of Ride</th>
	            <th>Status</th>
	            <th>Action</th>
	        </tr>
	        <%
	            List<Ride> requestedRides = (List<Ride>) request.getAttribute("requestedRides");
	            if (requestedRides != null && !requestedRides.isEmpty()) {
	                for (Ride ride : requestedRides) {
	        %>
	        <tr>
	            <td><%= ride.getId() %></td>
	            <td><%= ride.getStart_location() %></td>
	            <td><%= ride.getEnd_location() %></td>
	            <td><%= ride.getCustomer_username() %></td>
	            <td><%= ride.getPrice() %></td>
	            <td><%= ride.getLengthOfRide() %></td>
	            <td><%= ride.getRideStatus() %></td>
	            <td><button class="btn-bsic">Assign Rider</button></td>
	        </tr>
	        <%
	                }
	            } else {
	        %>
	        <tr>
	            <td colspan="8">No requested rides available.</td>
	        </tr>
	        <%
	            }
	        %>
	    </table>
    </div>
</body>
</html>