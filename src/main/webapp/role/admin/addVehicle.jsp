<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacity.models.User" %>
<html>
<head>
    <title>Add Vehicle</title>
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

    <h2>Add Vehicle to Gallery</h2>
    <form action="${pageContext.request.contextPath}/vehicle" method="post" enctype="multipart/form-data">
        Name: <input type="text" name="name"><br>
        Type: <input type="text" name="type"><br>
        Description: <textarea name="description"></textarea><br>
        Image: <input type="file" name="image"><br>
        <input type="submit" value="Add Vehicle">
    </form>
</body>
</html>