<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
</head>
<body>
    <%
        // Use the implicit 'session' object directly
        if (session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
        } else {
            response.sendRedirect("../../login.jsp");
        }
    %>

    <!-- Include the header -->
    <jsp:include page="header.jsp" />

    <main>
        <h1>Welcome to the <%= session.getAttribute("role") != null ? session.getAttribute("role") : "Guest" %> Home Page!</h1>
    </main>
</body>
</html>