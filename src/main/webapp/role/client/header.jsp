<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/header.css">
<header class="site-header">
    <nav class="navbar">
        <div class="logo">
            <a href="index.jsp">MegaCity</a>
        </div>
        <ul class="nav-links">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="bookRide.jsp">Rides</a></li>
            <li><a href="viewRides.jsp">View Rides</a></li>
            <li><a href="">Contact</a></li>
        </ul>
        <div class="user-info">
            <span class="username">
                <%= session.getAttribute("username") != null ? session.getAttribute("username") : "Guest" %>
            </span>
            <a class="logout-btn" href="../../logout.jsp">Logout</a>
        </div>
    </nav>
</header>