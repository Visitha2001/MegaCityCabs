<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Assign Rider</title>
</head>
<body>
    <h2>Assign Rider to a Ride</h2>
    <form action="RideServlet" method="post">
        <input type="hidden" name="action" value="assign">
        Ride ID: <input type="number" name="ride_id" required><br><br>
        Rider Username: <input type="text" name="rider_username" required><br><br>
        Vehicle Type: 
        <select name="vehicle_type" required>
            <option value="SEDAN">Sedan</option>
            <option value="SUV">SUV</option>
            <option value="BIKE">Bike</option>
        </select><br><br>
        Vehicle Plate Number: <input type="text" name="vehicle_plate_number" required><br><br>
        <button type="submit">Assign Rider</button>
    </form>
</body>
</html>