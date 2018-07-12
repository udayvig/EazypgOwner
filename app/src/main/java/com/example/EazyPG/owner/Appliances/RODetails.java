package com.example.EazyPG.owner.Appliances;

public class RODetails {

    public String roomNo, brand, model, timeSinceInstallation, capacity;

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