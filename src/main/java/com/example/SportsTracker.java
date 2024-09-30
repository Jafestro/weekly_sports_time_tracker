package com.example;

import java.util.ArrayList;

public class SportsTracker {
    private final ArrayList<Activity> activities = new ArrayList<>();

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    // Method to add a new activity
    public void logActivity(String name, int minutes) {
        activities.add(new Activity(name, minutes));
        System.out.println("Activity logged: " + name + " for " + minutes + " minutes.");
    }

    // Method to display all activities
    public void viewActivities() {
        if (activities.isEmpty()) {
            System.out.println("No activities logged yet.");
        } else {
            System.out.println("Logged activities:");
            for (Activity activity : activities) {
                System.out.println(activity.name + " - " + activity.minutes + " minutes");
            }
        }
    }

    // Method to calculate total time spent on sports
    public void calculateTotalTime() {
        int totalMinutes = 0;
        for (Activity activity : activities) {
            totalMinutes += activity.minutes;
        }
        System.out.println("Total time spent on sports this week: " + totalMinutes + " minutes.");
    }
}
