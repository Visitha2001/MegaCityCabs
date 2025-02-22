<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Vehicle</title>
</head>
<body>
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