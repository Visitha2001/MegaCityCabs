<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/role/client/css/header.css">
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
            <a href="${pageContext.request.contextPath}/role/client/index.jsp">MegaCity</a>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/role/client/index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/role/client/bookRide.jsp">Rides</a></li>
            <li><a href="${pageContext.request.contextPath}/user-rides">View Rides</a></li>
            <li><a href="">Contact</a></li>
        </ul>
        <div class="user-info">
            <span class="username">
                &#128100; <%= session.getAttribute("username") != null ? session.getAttribute("username") : "" %>
            </span>
            <% if (session.getAttribute("username") != null) { %>
                <a class="logout-btn" href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
            <% } else { %>
                <a class="login-btn" href="${pageContext.request.contextPath}/login.jsp">Login</a>
                <a class="reg-btn" href="${pageContext.request.contextPath}/registration.jsp">Register</a>
            <% } %>
        </div>
    </nav>
</header>

<!-- Back to Top Button -->
<button onclick="topFunction()" id="backToTopBtn" title="Back to Top">&#8593;</button>

<script>
// Get the button
let backToTopBtn = document.getElementById("backToTopBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {
    scrollFunction();
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        backToTopBtn.style.opacity = "1"; // Smoothly show the button
        backToTopBtn.style.visibility = "visible";
    } else {
        backToTopBtn.style.opacity = "0"; // Smoothly hide the button
        backToTopBtn.style.visibility = "hidden";
    }
}

// When the user clicks on the button, scroll to the top of the document smoothly
function topFunction() {
    window.scrollTo({
        top: 0,
        behavior: 'smooth' // Smooth scrolling
    });
}
</script>

<style>
/* Back to Top Button Styles */
#backToTopBtn {
    display: block;
    position: fixed;
    bottom: 20px;
    right: 20px;
    z-index: 99;
    font-size: 18px;
    background-color: #333;
    color: white;
    border: 1px solid #f1c40f;
    padding: 15px 18px;
    border-radius: 50%;
    cursor: pointer;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
    opacity: 0; /* Initially hidden */
    visibility: hidden;
    transition: opacity 0.3s ease, background-color 0.3s ease; /* Smooth transition */
}

#backToTopBtn:hover {
    background-color: #555; /* Darker on hover */
}
</style>