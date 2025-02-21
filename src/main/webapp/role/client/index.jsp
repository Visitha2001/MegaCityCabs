<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/role/client/css/index.css">
    <title>Home</title>
    <script src="${pageContext.request.contextPath}/role/client/js/slide.js" defer></script>
</head>
<body>
    <!-- Include the header -->
    <jsp:include page="header.jsp" />

    <div class="slider-container">
	    <div class="slider" id="slider">
		      <div class="slide">
			      <h2>Welcome to MegaCity Cab Service</h2>
			      <p>Book a ride and travel with comfort and safety.</p>
			      <p class="small-text">We are available 24/7 for your convenience.</p>
			      <a class="book-button" href="${pageContext.request.contextPath}/role/client/bookRide.jsp">Book a Ride</a>
			  </div>
			  <div class="slide" style="color: black">
			      <h2>Fast and Reliable</h2>
			      <p>Experience the fastest rides in town.</p>
			      <p class="small-text">Our drivers are trained to get you there on time.</p>
			  </div>
			  <div class="slide">
			      <h2>Affordable Prices</h2>
			      <p>Enjoy budget-friendly cab services.</p>
			      <p class="small-text">No hidden fees, just transparent pricing.</p>
			  </div>
	    </div>
	    <div class="slider-nav" id="sliderNav">
		      <button class="active" data-index="0"></button>
		      <button data-index="1"></button>
		      <button data-index="2"></button>
	    </div>
  	</div>
</body>
</html>