<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Riders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/admin/css/allrides.css">
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("../../login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Retrieve the list of riders from the request attributes
        List<User> riders = (List<User>) request.getAttribute("riders");
    %>
    <!-- Include the header -->
    <jsp:include page="header.jsp" />
    
    <div class="container_big">
        <h1>All Riders</h1>
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>NIC</th>
                <th>Address</th>
                <th>Vehicle Type</th>
                <th>Plate Number</th>
            </tr>
            <%
                if (riders != null && !riders.isEmpty()) {
                    for (User rider : riders) {
            %>
            <tr>
                <td><%= rider.getUsername() %></td>
                <td><%= rider.getEmail() %></td>
                <td><%= rider.getMobile() %></td>
                <td><%= rider.getNic() %></td>
                <td><%= rider.getAddress() %></td>
                <td><%= rider.getVehicleType() %></td>
                <td><%= rider.getPlateNumber() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">No riders available.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
