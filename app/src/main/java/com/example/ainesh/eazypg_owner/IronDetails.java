package com.example.ainesh.eazypg_owner;

public class IronDetails {

    private String roomNo, brand, model, timeSinceInstallation, capacity, inputPower;

    public IronDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String inputPower) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.inputPower = inputPower;
    }

    public IronDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        inputPower = "";
    }
}
