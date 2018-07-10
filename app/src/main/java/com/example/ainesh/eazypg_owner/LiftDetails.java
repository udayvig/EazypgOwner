package com.example.ainesh.eazypg_owner;

public class LiftDetails {

    String roomNo, brand, model, timeSinceInstallation, capacity, doorType;

    public LiftDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String doorType) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.doorType = doorType;

    }

    public LiftDetails() {
        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        doorType = "";
    }
}
