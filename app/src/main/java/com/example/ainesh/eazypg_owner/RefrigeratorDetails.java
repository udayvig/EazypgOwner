package com.example.ainesh.eazypg_owner;

public class RefrigeratorDetails {

    public String roomNo, brand, model, timeSinceInstallation, capacity, typeOfRefrigerator, starRating;

    public RefrigeratorDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String typeOfRefrigerator, String starRating) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.typeOfRefrigerator = typeOfRefrigerator;
        this.starRating = starRating;
    }

    public RefrigeratorDetails() {
        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        typeOfRefrigerator = "";
        starRating = "";
    }
}
