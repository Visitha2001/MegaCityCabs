<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Customers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/admin/css/allrides.css">
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("../../login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Retrieve the list of customers from the request attributes
        List<User> customers = (List<User>) request.getAttribute("customers");
    %>
    <!-- Include the header -->
    <jsp:include page="header.jsp" />
    
    <div class="container_big">
        <h1>All Customers</h1>
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>NIC</th>
                <th>Address</th>
            </tr>
            <%
                if (customers != null && !customers.isEmpty()) {
                    for (User customer : customers) {
            %>
            <tr>
                <td><%= customer.getUsername() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getMobile() %></td>
                <td><%= customer.getNic() %></td>
                <td><%= customer.getAddress() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No customers available.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
