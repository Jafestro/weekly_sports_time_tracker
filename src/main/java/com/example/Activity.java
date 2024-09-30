package com.example;

public class Activity {
    private String name;
    private int minutes;

    Activity(String name, int minutes) {
        this.name = name;
        this.minutes = minutes;
    }

    public String getName() {
        return name;
    }

    public int getMinutes() {
        return minutes;
    }
}