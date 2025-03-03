<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rider Dashboard</title>
    <!-- Include Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* Dark Theme Styles */
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
            margin: 20px;            
            max-width: 1200px;
            margin: 20px auto;
        }
        .stat-card {
            background-color: #1e1e1e;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
        }
        .stat-card h3 {
            margin: 0;
            font-size: 1.5em;
        }
        .stat-card p {
            margin: 10px 0 0;
            font-size: 2em;
            color: #ffffff;
        }
        .chart-container {
            margin-top: 20px;
            background-color: #1e1e1e;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);      
            max-width: 1200px;
            margin: 20px auto;
        }
        canvas {
            min-height: 350px; /* Set chart height to 200px */
        }
    </style>
</head>
<body>
    <%
        if (session == null || session.getAttribute("username") == null || !"rider".equals(session.getAttribute("role"))) {
            response.sendRedirect("../../login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
    %>

    <!-- Include the header -->
    <jsp:include page="header.jsp" />

    <main>
        <h1>Welcome to the Rider Dashboard, <%= username %>!</h1>
        <div class="stats-grid">
            <div class="stat-card">
                <h3>Total Completed Rides</h3>
                <p>${totalCompletedRides}</p>
            </div>
            <div class="stat-card">
                <h3>Total Income Earned</h3>
                <p>$<%= String.format("%.2f", request.getAttribute("totalIncome")) %></p>
            </div>
            <div class="stat-card">
                <h3>Total Assigned Rides</h3>
                <p>${totalAssignedRides}</p>
            </div>
            <div class="stat-card">
                <h3>Total Accepted Rides</h3>
                <p>${totalAcceptedRides}</p>
            </div>
        </div>

        <!-- Graph Section -->
        <div class="chart-container">
            <canvas id="ridesChart"></canvas>
        </div>
    </main>

    <script>
        // Data for the chart (dynamic from JSP variables)
        const ridesData = {
            labels: ["Completed", "Assigned", "Accepted"],
            datasets: [{
                label: "Rides Overview",
                data: [
                    ${totalCompletedRides}, // Completed Rides
                    ${totalAssignedRides},  // Assigned Rides
                    ${totalAcceptedRides}   // Accepted Rides
                ],
                backgroundColor: [
                    "#bb86fc", // Purple
                    "#03dac6", // Teal
                    "#ff7597"  // Pink
                ],
                borderColor: [
                    "#bb86fc",
                    "#03dac6",
                    "#ff7597"
                ],
                borderWidth: 1
            }]
        };

        // Chart configuration
        const config = {
            type: "bar", // Bar chart
            data: ridesData,
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: true,
                        position: "top",
                        labels: {
                            color: "#a0a0a0", // Muted text color for legend
                            font: {
                                size: 14
                            }
                        }
                    }
                },
                scales: {
                    x: {
                        ticks: {
                            color: "#a0a0a0", // Muted text color for x-axis
                            font: {
                                size: 14
                            }
                        },
                        grid: {
                            color: "#333333" // Dark grid lines
                        }
                    },
                    y: {
                        ticks: {
                            color: "#a0a0a0", // Muted text color for y-axis
                            font: {
                                size: 14
                            },
                            beginAtZero: true
                        },
                        grid: {
                            color: "#333333" // Dark grid lines
                        }
                    }
                }
            }
        };

        // Render the chart
        const ctx = document.getElementById("ridesChart").getContext("2d");
        new Chart(ctx, config);
    </script>
</body>
</html>