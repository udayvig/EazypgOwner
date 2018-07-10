package com.example.ainesh.eazypg_owner;

public class GeyserDetails {

    public String roomNo, brand, model, timeSinceInstallation, capacity, inputPower, starRating;

    public GeyserDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String inputPower, String starRating) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.inputPower = inputPower;
        this.starRating = starRating;
    }

    public GeyserDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        inputPower = "";
        starRating = "";
    }
}
