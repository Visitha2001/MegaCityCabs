<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.Ride" %>

<!DOCTYPE html>
<html>
<head>
    <title>Assign Ride</title>
	<style>
	    .container_big {
	    	margin: 50px;
	    }
	    table {
	        width: 100%;
	        border-collapse: collapse;
	        margin-top: 20px;
	        background-color: #1f1f1f; /* Dark table background */
	        color: #ffffff; /* Light text color for table */
	    }
	    table, th, td {
	        border: 1px solid #333; /* Darker border color */
	    }
	    th, td {
	        padding: 10px;
	        text-align: left;
	    }
	    th {
	        background-color: #333; /* Darker header background */
	        color: #f0f0f0; /* Light text color for headers */
	    }
	    tr:hover {
	        background-color: #2a2a2a; /* Slightly lighter hover effect */
	    }
	</style>
    <link rel="stylesheet" href="css/header.css">
</head>
<body>
    <%
        // Use the implicit 'session' object directly
        if (session != null && session.getAttribute("username") != null || !"admin".equals(session.getAttribute("role"))) {
            String username = (String) session.getAttribute("username");
        } else {
            response.sendRedirect("../../login.jsp");
        }
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
	            <td><button>Assign Rider</button></td>
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