<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <!-- Include Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* Existing styles */
        .dashboard-container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
        }

        .dashboard-cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }

        .dashboard-card {
            background-color: #1e1e1e;
            border: 1px solid #333;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 10px rgba(0, 0, 0, 0.4);
        }

        .card-icon {
            font-size: 2.5rem;
            margin-bottom: 10px;
        }

        .stats-value {
            font-size: 1.8rem;
            font-weight: bold;
        }

        .text-muted {
            color: #a0a0a0;
        }

        .success-message {
            padding: 15px;
            border-radius: 5px;
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
            font-size: 16px;
            text-align: center;
            margin-bottom: 20px;
        }

        /* Chart Styling */
        .chart-container {
            margin-top: 20px;
            background-color: #1e1e1e;
            border: 1px solid #333;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
            height: 400px;
        }
    </style>
</head>
<body>
    <!-- Include the header -->
    <jsp:include page="header.jsp" />

    <!-- Dashboard Content -->
    <div class="dashboard-container">
        <h1>Dashboard Overview</h1>

        <!-- Cards -->
        <div class="dashboard-cards">
            <!-- Total Income Card -->
            <div class="dashboard-card">
                <div class="d-flex">
                    <div>
                        <h5 class="text-muted">Total Income</h5>
                        <p class="stats-value text-success">$${totalIncome}</p>
                    </div>
                    <div class="card-icon text-success">
                        <i class="bi bi-cash-stack"></i>
                    </div>
                </div>
            </div>

            <!-- Total Rides Card -->
            <div class="dashboard-card">
                <div class="d-flex">
                    <div>
                        <h5 class="text-muted">Total Rides</h5>
                        <p class="stats-value text-primary">${totalRides}</p>
                    </div>
                    <div class="card-icon text-primary">
                        <i class="bi bi-car-front-fill"></i>
                    </div>
                </div>
            </div>

            <!-- Completed Rides Card -->
            <div class="dashboard-card">
                <div class="d-flex">
                    <div>
                        <h5 class="text-muted">Completed Rides</h5>
                        <p class="stats-value text-info">${completedRides}</p>
                    </div>
                    <div class="card-icon text-info">
                        <i class="bi bi-check-circle"></i>
                    </div>
                </div>
            </div>

            <!-- Requested Rides Card -->
            <div class="dashboard-card">
                <div class="d-flex">
                    <div>
                        <h5 class="text-muted">Requested Rides</h5>
                        <p class="stats-value text-warning">${requestedRides}</p>
                    </div>
                    <div class="card-icon text-warning">
                        <i class="bi bi-hourglass-split"></i>
                    </div>
                </div>
            </div>

            <!-- Assigned Rides Card -->
            <div class="dashboard-card">
                <div class="d-flex">
                    <div>
                        <h5 class="text-muted">Assigned Rides</h5>
                        <p class="stats-value text-secondary">${assignedRides}</p>
                    </div>
                    <div class="card-icon text-secondary">
                        <i class="bi bi-person-check"></i>
                    </div>
                </div>
            </div>

            <!-- Total Riders Card -->
            <div class="dashboard-card">
                <div class="d-flex">
                    <div>
                        <h5 class="text-muted">Total Riders</h5>
                        <p class="stats-value text-danger">${totalRiders}</p>
                    </div>
                    <div class="card-icon text-danger">
                        <i class="bi bi-people"></i>
                    </div>
                </div>
            </div>

            <!-- Total Customers Card -->
            <div class="dashboard-card">
                <div class="d-flex">
                    <div>
                        <h5 class="text-muted">Total Customers</h5>
                        <p class="stats-value text-warning">${totalCustomers}</p>
                    </div>
                    <div class="card-icon text-warning">
                        <i class="bi bi-person"></i>
                    </div>
                </div>
            </div>
        </div>

        <!-- Chart Section -->
        <div class="chart-container">
            <canvas id="ridesChart" width="400" height="200"></canvas>
        </div>
    </div>

    <!-- Chart.js Script -->
    <script>
        // Extract data from JSP variables
        const totalRides = ${totalRides};
        const completedRides = ${completedRides};
        const requestedRides = ${requestedRides};
        const assignedRides = ${assignedRides};

        // Initialize the chart
        const ctx = document.getElementById('ridesChart').getContext('2d');
        const ridesChart = new Chart(ctx, {
            type: 'bar', // Bar chart type
            data: {
                labels: ['Total Rides', 'Completed Rides', 'Requested Rides', 'Assigned Rides'],
                datasets: [{
                    label: 'Rides Overview',
                    data: [totalRides, completedRides, requestedRides, assignedRides],
                    backgroundColor: [
                        'rgba(75, 192, 192, 0.6)', // Teal
                        'rgba(54, 162, 235, 0.6)', // Blue
                        'rgba(255, 206, 86, 0.6)', // Yellow
                        'rgba(153, 102, 255, 0.6)' // Purple
                    ],
                    borderColor: [
                        'rgba(75, 192, 192, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(153, 102, 255, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            color: '#a0a0a0' // Muted text color for dark theme
                        }
                    },
                    x: {
                        ticks: {
                            color: '#a0a0a0' // Muted text color for dark theme
                        }
                    }
                },
                plugins: {
                    legend: {
                        labels: {
                            color: '#a0a0a0' // Muted text color for dark theme
                        }
                    }
                }
            }
        });
    </script>
</body>
</html>