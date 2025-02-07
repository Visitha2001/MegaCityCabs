<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Accept a Ride</title>
</head>
<body>
    <h2>Accept a Ride</h2>
    <form action="RideServlet" method="post">
        <input type="hidden" name="action" value="accept">
        Ride ID: <input type="number" name="ride_id" required><br><br>
        <button type="submit">Accept Ride</button>
    </form>
</body>
</html>