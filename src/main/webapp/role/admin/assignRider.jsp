<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.Ride" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assign Ride</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
    <h1>Assign Ride</h1>
    <p>Below is the list of all requested rides:</p>

    <table>
        <thead>
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
        </thead>
        <tbody>
            <% 
                List<Ride> requestedRides = (List<Ride>) request.getAttribute("requestedRides");
                if (requestedRides != null) {
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
                <td>
                    <button onclick="assignRider(<%= ride.getId() %>)">Assign Rider</button>
                </td>
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
        </tbody>
    </table>

    <script>
        function assignRider(rideId) {
            alert("Assigning rider to ride ID: " + rideId);
            // You can implement AJAX or redirect to another page to handle assignment
        }
    </script>
</body>
</html>
