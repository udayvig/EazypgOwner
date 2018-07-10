package com.example.ainesh.eazypg_owner;

public class HeaterDetails {

    private String roomNo, brand, model, timeSinceInstallation, inputPower, weight;

    public HeaterDetails(String roomNo, String brand, String model, String timeSinceInstallation, String inputPower, String weight) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.inputPower = inputPower;
        this.weight = weight;
    }

    public HeaterDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        inputPower = "";
        weight = "";

    }
}
