package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;

public class Test {
     private SportsTracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new SportsTracker();
    }

    @Test
    void testLogActivity() {
        tracker.logActivity("Running", 30);
        assertEquals(1, tracker.getActivities().size(), "Activity list should have 1 activity.");
        assertEquals("Running", tracker.getActivities().get(0).name, "First activity should be 'Running'.");
        assertEquals(30, tracker.getActivities().get(0).minutes, "First activity time should be 30 minutes.");
    }

    @Test
    void testViewActivities_noActivities() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        tracker.viewActivities();

        assertEquals("No activities logged yet.\n", outputStream.toString(), "No activities should be logged.");
    }

    @Test
    void testViewActivities_withActivities() {
        tracker.logActivity("Swimming", 45);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        tracker.viewActivities();

        String expectedOutput = "Logged activities:\nSwimming - 45 minutes\n";
        assertEquals(expectedOutput, outputStream.toString(), "View activities should display logged activity.");
    }

    @Test
    void testCalculateTotalTime_noActivities() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        tracker.calculateTotalTime();

        assertEquals("Total time spent on sports this week: 0 minutes.\n", outputStream.toString(), "Total time should be 0.");
    }

    @Test
    void testCalculateTotalTime_withActivities() {
        tracker.logActivity("Cycling", 20);
        tracker.logActivity("Running", 40);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        tracker.calculateTotalTime();

        assertEquals("Total time spent on sports this week: 60 minutes.\n", outputStream.toString(), "Total time should be 60 minutes.");
    }
}
