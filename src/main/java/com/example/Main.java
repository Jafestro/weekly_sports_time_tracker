package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SportsTracker tracker = new SportsTracker();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            
            while (running) {
                System.out.println("1. Log an activity");
                System.out.println("2. View activities");
                System.out.println("3. Calculate total time");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter activity name: ");
                        String name = scanner.next();
                        System.out.print("Enter time in minutes: ");
                        int minutes = scanner.nextInt();
                        tracker.logActivity(name, minutes);
                    }
                    case 2 -> tracker.viewActivities();
                    case 3 -> tracker.calculateTotalTime();
                    case 4 -> {
                        running = false;
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid option. Please choose again.");
                }
            }
        }
    }
}
