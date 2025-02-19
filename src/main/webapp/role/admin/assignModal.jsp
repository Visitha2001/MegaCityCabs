<div id="assignModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">×</span>
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
            </select><br>

            <input type="hidden" id="plateNumber" name="plateNumber" value="">
            <input type="hidden" id="mobile" name="mobile" value="">

            <button type="submit" class="btn-bsic">Assign</button>
        </form>
    </div>
</div>