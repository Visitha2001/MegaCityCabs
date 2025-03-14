package com.megacity.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import com.megacity.models.Ride;
import com.megacity.services.impl.DashboardServiceImpl;

class DashboardServiceTest {

    private static final Logger logger = Logger.getLogger(DashboardServiceTest.class.getName());
    private DashboardService dashboardService = new DashboardServiceImpl();

    @Test
    void testGetTotalRidesCount() throws SQLException {
        int totalRides = dashboardService.getTotalRidesCount();
        logger.info("Total Rides Count: " + totalRides);
        assertTrue(totalRides >= 0, "Total rides count should be non-negative");
    }

    @Test
    void testGetTotalIncome() throws SQLException {
        double totalIncome = dashboardService.getTotalIncome();
        logger.info("Total Income: " + totalIncome);
        assertTrue(totalIncome >= 0, "Total income should be non-negative");
    }

    @Test
    void testGetRecentRides() throws SQLException {
        List<Ride> recentRides = dashboardService.getRecentRides(5);
        logger.info("Recent Rides: " + recentRides);
        assertNotNull(recentRides, "Recent rides list should not be null");
        assertTrue(recentRides.size() <= 5, "Recent rides list should not exceed the limit");
    }
}