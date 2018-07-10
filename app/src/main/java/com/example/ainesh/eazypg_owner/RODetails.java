package com.example.ainesh.eazypg_owner;

public class RODetails {

    private String roomNo, brand, model, timeSinceInstallation, capacity;

    public RODetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
    }

    public RODetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
    }
}
