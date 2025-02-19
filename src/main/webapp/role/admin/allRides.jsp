<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.Ride" %>
<%@ page import="com.megacity.models.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Assign Ride</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/admin/css/allrides.css">
    <style>
        /* Modal styles */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgba(0, 0, 0, 0.8); /* Darker background */
        }
        
        .modal-content {
            background-color: #2c2c2c; /* Dark gray background */
            margin: 10% auto; /* 10% from the top and centered */
            padding: 20px;
            border: 1px solid #444; /* Dark border */
            border-radius: 10px;
            width: 50%; /* Adjust width as needed */
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5); /* Slightly darker shadow */
            color: #f1f1f1; /* Light text color */
        }
        
        .close {
            color: #888; /* Lighter close button */
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }
        
        .close:hover,
        .close:focus {
            color: #f1f1f1; /* Change to lighter color on hover */
            text-decoration: none;
        }
        
        /* Form styles */
        .modal-content label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
            color: #f1f1f1; /* Light label color */
        }
        
        .modal-content input[type="text"],
        .modal-content select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #555; /* Darker border for inputs */
            border-radius: 4px;
            background-color: #444; /* Dark background for inputs */
            color: #f1f1f1; /* Light text color for inputs */
        }
        
        .modal-content input[type="text"]::placeholder {
            color: #bbb; /* Lighter placeholder text */
        }
        
        .modal-content button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: rgb(249, 192, 0);
            color: black;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        
        .modal-content button:hover {
            background-color: rgb(179, 137, 0);
        }
    </style>
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("../../login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        List<Ride> requestedRides = (List<Ride>) request.getAttribute("requestedRides");
        List<User> riders = (List<User>) request.getAttribute("riders");
    %>
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
                <th>Req. Vehicle</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <%
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
                <td><%= ride.getVehicleType() %></td>
                <td><%= ride.getRideStatus() %></td>
                <td>
                    <button class="btn-bsic" onclick="assignRider(
                    	<%= ride.getId() %>,
                    	'<%= ride.getStart_location() %>',
                    	'<%= ride.getEnd_location() %>',
                    	'<%= ride.getCustomer_username() %>',
                    	<%= ride.getPrice() %>,
                    	'<%= ride.getLengthOfRide() %>',
                    	'<%= ride.getVehicleType() %>'
                    )">Assign Rider</button>
                </td>
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

    <jsp:include page="assignModal.jsp" />

    <script>
        function closeModal() {
            document.getElementById("assignModal").style.display = "none";
        }

        function assignRider(rideId, startLocation, endLocation, customerUsername, price, lengthOfRide, vehicleType) {
            document.getElementById("rideId").value = rideId;
            document.getElementById("startLocation").value = startLocation;
            document.getElementById("endLocation").value = endLocation;
            document.getElementById("customerUsername").value = customerUsername;
            document.getElementById("price").value = price;
            document.getElementById("lengthOfRide").value = lengthOfRide;
            
            fetchRidersByVehicleType(vehicleType);
            document.getElementById("assignModal").style.display = "block";
        }

        function fetchRidersByVehicleType(vehicleType) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "${pageContext.request.contextPath}/user?vehicleType=" + vehicleType, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var riders = JSON.parse(xhr.responseText);
                    var select = document.getElementById("riderUsername");
                    select.innerHTML = ""; // Clear existing options
                    riders.forEach(function (rider) {
                        var option = document.createElement("option");
                        option.value = rider.username;
                        option.text = rider.username + " - " + rider.vehicleType + " - " + rider.mobile + " (" + rider.plateNumber + ")";
                        option.setAttribute("data-plate-number", rider.plateNumber);
                        option.setAttribute("data-mobile", rider.mobile);
                        select.appendChild(option);
                    });

                    // Update plate number and mobile when a rider is selected
                    select.addEventListener("change", function () {
                        var selectedOption = select.options[select.selectedIndex];
                        document.getElementById("plateNumber").value = selectedOption.getAttribute("data-plate-number");
                        document.getElementById("mobile").value = selectedOption.getAttribute("data-mobile");
                    });
                }
            };
            xhr.send();
        }
    </script>
</body>
</html>
