<div id="assignModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Assign Rider</h2>
        <form action="${pageContext.request.contextPath}/assignRide" method="post">
            <input type="hidden" id="rideId" name="rideId" value="">
            <label for="startLocation">Start Location:</label>
            <input type="text" id="startLocation" name="startLocation" readonly><br>

            <label for="endLocation">End Location:</label>
            <input type="text" id="endLocation" name="endLocation" readonly><br>

            <label for="customerUsername">Customer Username:</label>
            <input type="text" id="customerUsername" name="customerUsername" readonly><br>

            <label for="price">Price:</label>
            <input type="text" id="price" name="price" readonly><br>

            <label for="lengthOfRide">Length of Ride:</label>
            <input type="text" id="lengthOfRide" name="lengthOfRide" readonly><br>

            <label for="riderUsername">Select Rider:</label>
            <select id="riderUsername" name="riderUsername" required>
                <option value="">-- Select Rider --</option>
                <!-- Populate this dropdown with available riders -->
            </select><br>

            <input type="hidden" id="plateNumber" name="plateNumber" value="">

            <button type="submit" class="btn-bsic">Assign</button>
        </form>
    </div>
</div>

<script>
    // Function to close the modal
    function closeModal() {
        document.getElementById("assignModal").style.display = "none";
    }

    // Function to populate the dropdown and pre-fill ride details
    function assignRider(rideId, startLocation, endLocation, customerUsername, price, lengthOfRide, vehicleType) {
        document.getElementById("rideId").value = rideId;
        document.getElementById("startLocation").value = startLocation;
        document.getElementById("endLocation").value = endLocation;
        document.getElementById("customerUsername").value = customerUsername;
        document.getElementById("price").value = price;
        document.getElementById("lengthOfRide").value = lengthOfRide;

        // Fetch riders based on vehicle type using AJAX
        fetchRidersByVehicleType(vehicleType);

        // Show the modal
        document.getElementById("assignModal").style.display = "block";
    }

    // Function to fetch riders by vehicle type
    function fetchRidersByVehicleType(vehicleType) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "${pageContext.request.contextPath}/user?vehicleType=" + vehicleType, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var riders = JSON.parse(xhr.responseText);
                var select = document.getElementById("riderUsername");
                select.innerHTML = ""; // Clear existing options
                riders.forEach(function (rider) {
                    var option = document.createElement("option");
                    option.value = rider.username;
                    option.text = rider.username + " - " + rider.vehicleType + " (" + rider.plateNumber + ")";
                    option.setAttribute("data-plate-number", rider.plateNumber); // Store plate number in data attribute
                    select.appendChild(option);
                });

                // Add event listener to update the hidden plate number field when a rider is selected
                select.addEventListener("change", function () {
                    var selectedOption = select.options[select.selectedIndex];
                    document.getElementById("plateNumber").value = selectedOption.getAttribute("data-plate-number");
                });
            }
        };
        xhr.send();
    }
</script>