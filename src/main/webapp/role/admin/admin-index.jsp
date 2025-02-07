<%
    // Use the implicit 'session' object directly
    if (session != null && session.getAttribute("username") != null) {
        String username = (String) session.getAttribute("username");
        out.println("<h3>Welcome, " + username + "!</h3>");
    } else {
        response.sendRedirect("login.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin-Dashboard</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header class="site-header">
        <nav class="navbar">
            <div class="logo">
                <a href="index.jsp">MyApp</a>
            </div>
            <ul class="nav-links">
                <li><a href="">Customers</a></li>
                <li><a href="">Manage Rides</a></li>
            </ul>
			<div class="user-info">
			    <span class="username"><%= session.getAttribute("username") != null ? session.getAttribute("username") : "Guest" %></span>
			    <a class="logout-btn" href="logout.jsp">Logout</a>
			</div>
        </nav>
    </header>

    <main>
        <h1>Welcome to the <%= session.getAttribute("role") != null ? session.getAttribute("role") : "Guest" %> Home Page!</h1>
    </main>
</body>
</html>