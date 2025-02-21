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
        if (session == null || session.getAttribute("username") == null || !"customer".equals(session.getAttribute("role"))) {
            response.sendRedirect("../../login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
    %>

    <!-- Include the header -->
    <jsp:include page="header.jsp" />

    <main>
    	
    </main>
</body>
</html>