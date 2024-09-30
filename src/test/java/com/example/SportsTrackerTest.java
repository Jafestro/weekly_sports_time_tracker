package com.example;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SportsTrackerTest {

    private SportsTracker sportsTracker;

    @BeforeEach
    void setUp() {
        sportsTracker = new SportsTracker();
    }

    @Test
    void testLogActivity() {
        // Arrange
        String activityName = "Running";
        int activityMinutes = 30;

        // Act
        sportsTracker.logActivity(activityName, activityMinutes);

        // Assert
        ArrayList<Activity> activities = sportsTracker.getActivities();
        assertEquals(1, activities.size());
        assertEquals(activityName, activities.get(0).getName());
        assertEquals(activityMinutes, activities.get(0).getMinutes());
    }

    @Test
    void testViewActivitiesWithNoActivities() {
        // Act & Assert
        assertEquals(0, sportsTracker.getActivities().size(), "No activities should be logged initially.");
    }

    @Test
    void testViewActivitiesWithLoggedActivities() {
        // Arrange
        sportsTracker.logActivity("Swimming", 45);
        sportsTracker.logActivity("Cycling", 60);

        // Act
        ArrayList<Activity> activities = sportsTracker.getActivities();

        // Assert
        assertEquals(2, activities.size());
        assertEquals("Swimming", activities.get(0).getName());
        assertEquals(45, activities.get(0).getMinutes());
        assertEquals("Cycling", activities.get(1).getName());
        assertEquals(60, activities.get(1).getMinutes());
    }

    @Test
    void testCalculateTotalTimeWithNoActivities() {
        // Capture the expected output when no activities are logged
        // Since no activities are logged, total time should be 0
        int expectedTotalMinutes = 0;

        // Act
        int actualTotalMinutes = sportsTracker.getActivities().stream()
            .mapToInt(activity -> activity.getMinutes())
            .sum();

        // Assert
        assertEquals(expectedTotalMinutes, actualTotalMinutes, "Total time should be 0 if no activities are logged.");
    }

    @Test
    void testCalculateTotalTimeWithLoggedActivities() {
        // Arrange
        sportsTracker.logActivity("Yoga", 30);
        sportsTracker.logActivity("Jogging", 40);

        // Act
        int totalMinutes = sportsTracker.getActivities().stream()
            .mapToInt(activity -> activity.getMinutes())
            .sum();

        // Assert
        assertEquals(70, totalMinutes, "Total time should match the sum of logged activity times.");
    }
}
