<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/role/admin/css/header.css">
<header class="site-header">
    <nav class="navbar">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/role/admin/admin-index.jsp">MegaCity</a>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/role/admin/admin-index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/ride">Assign Ride</a></li>
            <li><a href="${pageContext.request.contextPath}/assigned">Assigned Rides</a></li>
            <li><a href="${pageContext.request.contextPath}/role/admin/addVehicle.jsp">Add Vehicle</a></li>
        </ul>
        <div class="user-info">
            <div class="user-info">
            <span class="username">
                <%= session.getAttribute("username") != null ? session.getAttribute("username") : "Guest" %>
            </span>
            <% if (session.getAttribute("username") != null) { %>
                <a class="logout-btn" href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
            <% } else { %>
                <a class="login-btn" href="${pageContext.request.contextPath}/login.jsp">Login</a>
            <% } %>
        </div>
    </nav>
</header>