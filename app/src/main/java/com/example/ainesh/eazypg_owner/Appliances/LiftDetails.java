package com.example.ainesh.eazypg_owner.Appliances;

public class LiftDetails {

    public String brand, model, timeSinceInstallation, capacity, doorType;

    public LiftDetails(String brand, String model, String timeSinceInstallation, String capacity, String doorType) {

        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.doorType = doorType;

    }

    public LiftDetails() {

        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        doorType = "";
    }
}
