package com.example.ainesh.eazypg_owner;

public class GeyserDetails {

    String roomNo, brand, model, timeSinceInstallation, capacity, inoutPower, starRating;

    public GeyserDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String inoutPower, String starRating) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.inoutPower = inoutPower;
        this.starRating = starRating;
    }

    public GeyserDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        inoutPower = "";
        starRating = "";
    }
}
