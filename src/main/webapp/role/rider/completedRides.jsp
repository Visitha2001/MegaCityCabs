<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.Ride" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Completed Rides</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/rider/css/allrides.css">
    <style>
        .bill-header {
            text-align: center;
            border-bottom: 2px solid #000;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        .bill-details {
            margin-bottom: 20px;
        }
        .bill-details p {
            margin: 5px 0;
        }
        .bill-footer {
            text-align: center;
            border-top: 2px solid #000;
            padding-top: 10px;
            margin-top: 20px;
        }

        /* Hide everything except the bill during printing */
        @media print {
            body * {
                visibility: hidden;
            }
            .bill-container, .bill-container * {
                visibility: visible;
            }
            .bill-container {
                position: absolute;
                left: 0;
                top: 0;
                width: 100%;
                border: none;
                box-shadow: none;
            }
            button {
                display: none;
            }
        }

        /* Modal Styles */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto; /* 5% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
            max-width: 600px; /* Maximum width */
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("../../login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Retrieve the list of completed rides for the user from the request attributes
        List<Ride> completedRides = (List<Ride>) request.getAttribute("completedRides");
    %>
    <!-- Include the header -->
    <jsp:include page="header.jsp" />
    
    <div class="container_big">
        <h1>Completed Rides</h1>
        <h2>Rides For <%= username %></h2>
        
        <table border="1" id="ridesTable">
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
                <th>Started At</th>
                <th>Ended At</th>
                <th>Action</th> <!-- New column for the Print Bill button -->
            </tr>
            <%
                if (completedRides != null && !completedRides.isEmpty()) {
                    for (Ride ride : completedRides) {
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
                <td><%= ride.getRideStartedAt() != null ? ride.getRideStartedAt().toString() : "N/A" %></td>
                <td><%= ride.getRideEndedAt() != null ? ride.getRideEndedAt().toString() : "N/A" %></td>
                <td>
                    <!-- Print Bill Button -->
                    <button onclick="openBill('<%= ride.getId() %>', '<%= ride.getStart_location() %>', '<%= ride.getEnd_location() %>', '<%= ride.getPrice() %>', '<%= ride.getLengthOfRide() %>', '<%= ride.getVehicleType() %>', '<%= ride.getCustomer_username() %>', '<%= ride.getRider_username() %>', '<%= ride.getVehiclePlateNumber() %>', '<%= ride.getRideStartedAt() != null ? ride.getRideStartedAt().toString() : "N/A" %>', '<%= ride.getRideEndedAt() != null ? ride.getRideEndedAt().toString() : "N/A" %>')" class="btn-bsic">Print Bill</button>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="13">No completed rides available.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    
    <!-- Modal -->
    <div id="billModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <div class="bill-container">
                <h1>Ride Bill</h1>
                <div class="bill-header">
                    <p><strong>Bill ID:</strong> <span id="billId"></span></p>
                </div>
                <div class="bill-details">
                    <h2>Ride Details</h2>
                    <p><strong>Start Location:</strong> <span id="startLocation"></span></p>
                    <p><strong>End Location:</strong> <span id="endLocation"></span></p>
                    <p><strong>Price:</strong> <span id="price"></span></p>
                    <p><strong>Length of Ride:</strong> <span id="lengthOfRide"></span></p>
                    <p><strong>Vehicle Type:</strong> <span id="vehicleType"></span></p>
                    <p><strong>Customer Username:</strong> <span id="customerUsername"></span></p>
                    <p><strong>Rider Username:</strong> <span id="riderUsername"></span></p>
                    <p><strong>Vehicle Plate No.:</strong> <span id="vehiclePlateNumber"></span></p>
                    <p><strong>Started At:</strong> <span id="startedAt"></span></p>
                    <p><strong>Ended At:</strong> <span id="endedAt"></span></p>
                </div>
                <div class="bill-footer">
                    <p>Thank you for using our service!</p>
                </div>
                <button onclick="printBill()" class="btn-bsic" style="margin-top: 30px; width: 100%">Print Bill</button>
            </div>
        </div>
    </div>

    <script>
        // Function to open the bill for a specific ride
        function openBill(rideId, startLocation, endLocation, price, lengthOfRide, vehicleType, customerUsername, riderUsername, vehiclePlateNumber, startedAt, endedAt) {
            // Populate the bill template
            document.getElementById('billId').innerText = rideId;
            document.getElementById('startLocation').innerText = startLocation;
            document.getElementById('endLocation').innerText = endLocation;
            document.getElementById('price').innerText = price;
            document.getElementById('lengthOfRide').innerText = lengthOfRide;
            document.getElementById('vehicleType').innerText = vehicleType;
            document.getElementById('customerUsername').innerText = customerUsername;
            document.getElementById('riderUsername').innerText = riderUsername;
            document.getElementById('vehiclePlateNumber').innerText = vehiclePlateNumber;
            document.getElementById('startedAt').innerText = startedAt;
            document.getElementById('endedAt').innerText = endedAt;

            // Display the modal
            document.getElementById('billModal').style.display = 'block';
        }

        // Function to close the modal
        function closeModal() {
            document.getElementById('billModal').style.display = 'none';
        }

        // Function to print the bill
        function printBill() {
            window.print();
        }

        // Close the modal if the user clicks outside of it
        window.onclick = function(event) {
            var modal = document.getElementById('billModal');
            if (event.target == modal) {
                closeModal();
            }
        }
    </script>
</body>
</html>