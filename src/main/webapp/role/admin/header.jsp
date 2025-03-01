<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/role/admin/css/header.css">
	<!-- Display success message if it exists -->
	<% 
	    String successMessage = (String) session.getAttribute("successMessage");
	    if (successMessage != null) {
	%>
	    <div style="
	        padding: 15px;
	        border-radius: 5px;
	        background-color: #d4edda;
	        border: 1px solid #c3e6cb;
	        color: #155724;
	        font-size: 16px;
	        font-family: Arial, sans-serif;
	        text-align: center;
	    ">
	        <%= successMessage %>
	    </div>
	<%
	        // Remove the message from the session after displaying it
	        session.removeAttribute("successMessage");
	    }
	%>
<header class="site-header">
    <nav class="navbar">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/admin/dashboard">MegaCity</a>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/admin/dashboard">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/ride">Assign Ride</a></li>
            <li><a href="${pageContext.request.contextPath}/assigned">Assigned Rides</a></li>
            <li><a href="${pageContext.request.contextPath}/riders">All Rides</a></li>
            <li><a href="${pageContext.request.contextPath}/customers">All Customers</a></li>
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