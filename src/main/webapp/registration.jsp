<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Sign Up</title>
    <style>
        .error-message {
            padding: 10px;
            margin: 10px 0;
            background-color: #ffaeae; /* Light red background */
            border: 1px solid #ffcdd2; /* Slightly darker red border */
            color: #c62828; /* Dark red text */
            font-size: 14px;
            font-family: Arial, sans-serif;
            text-align: center;
            border-radius: 5px;
        }
    </style>
	<!-- Font Icon -->
	<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
	<!-- Main css -->
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Display error message if it exists -->
    <% 
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <div class="error-message">
            <%= errorMessage %>
        </div>
    <%
            // Remove the error message from the session after displaying it
            session.removeAttribute("errorMessage");
        }
    %>
    <div class="main">
        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="post" action="register" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="Your Name" required/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email" required/>
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Password" required/>
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" required/>
                            </div>
                            <div class="form-group">
                                <label for="contact"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="contact" id="contact" placeholder="Contact no" required/>
                            </div>
                            <div class="form-group">
                                <label for="nic"><i class="zmdi zmdi-card"></i></label>
                                <input type="text" name="nic" id="nic" placeholder="Your NIC" required/>
                            </div>
                            <div class="form-group">
                                <label for="address"><i class="zmdi zmdi-pin"></i></label>
                                <input type="text" name="address" id="address" placeholder="Your Address" required/>
                            </div>
                            <div class="form-group">
                                <label for="role"><i class="zmdi zmdi-account-box"></i></label>
                                <select name="role" id="role" required>
                                    <option value="customer">Customer</option>
                                    <option value="rider">Rider</option>
                                </select>
                            </div>

                            <!-- Rider-specific fields (initially hidden) -->
                            <div id="riderFields" style="display: none;">
                                <div class="form-group">
								    <label for="vehicleType"><i class="zmdi zmdi-bike"></i></label>
								    <select name="vehicleType" id="vehicleType" class="form-select">
								        <option value="">Select Vehicle Type</option>
							            <option value="Car">Car</option>
							            <option value="Bike">Bike</option>
							            <option value="SUV">SUV</option>
							            <option value="Van">Van</option>
								    </select>
								</div>
                                <div class="form-group">
                                    <label for="plateNumber"><i class="zmdi zmdi-card"></i></label>
                                    <input type="text" name="plateNumber" id="plateNumber" placeholder="plate Number (V12345)" />
                                </div>
                            </div>

                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure>
                            <img src="images/signup-image.jpg" alt="sign up image">
                        </figure>
                        <a href="login.jsp" class="signup-image-link">I am already member</a>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>

    <script>
        // Function to show/hide rider fields based on role selection
        document.getElementById('role').addEventListener('change', function () {
            var riderFields = document.getElementById('riderFields');
            if (this.value === 'rider') {
                riderFields.style.display = 'block';
            } else {
                riderFields.style.display = 'none';
            }
        });
    </script>
</body>
</html>
