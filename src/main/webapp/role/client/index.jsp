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
  	
  	<section class="why-us-section">
	    <div class="why-us-content">
	      <h2>Why Choose Us?</h2>
	      <p>We provide the best cab services tailored to your needs. Here's why you should choose us:</p>
	      <ul>
	        <li>Safe and reliable rides with verified drivers</li>
	        <li>24/7 customer support for all your queries</li>
	        <li>Affordable pricing with no hidden charges</li>
	        <li>Modern vehicles equipped with top-notch amenities</li>
	      </ul>
	    </div>
	    <div class="why-us-image">
	      <img src="${pageContext.request.contextPath}/role/client/images/portrait-young-confused-blonde-woman-standing-busy-street-with-cars-her-looking.jpg" alt="Cab Service Image">
	   	</div>
  	</section>
  	<hr class="line2"/>
  	  	
  	<section class="services-section">
	    <h2>Our Services</h2>
	    <div class="services-container">
	      <div class="service-card">
	        <i class="fas fa-car"></i>
	        <h3>City Rides</h3>
  			<hr class="line"/>
	        <p>Enjoy seamless rides within the city at affordable prices.</p>
	      </div>
	      <div class="service-card">
	        <i class="fas fa-road"></i>
	        <h3>Inter-City Travel</h3>
  			<hr class="line"/>
	        <p>Travel comfortably between cities with our premium cabs.</p>
	      </div>
	      <div class="service-card">
	        <i class="fas fa-clock"></i>
	        <h3>24/7 Availability</h3>
  			<hr class="line"/>
	        <p>Book a cab anytime, anywhere, day or night.</p>
	      </div>
	      <div class="service-card">
	        <i class="fas fa-shield-alt"></i>
	        <h3>Safety First</h3>
  			<hr class="line"/>
	        <p>Your safety is our priority with verified drivers and secure rides.</p>
	      </div>
	    </div>
  	</section>
</body>
</html>