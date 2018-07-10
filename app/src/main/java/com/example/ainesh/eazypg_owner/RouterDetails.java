package com.example.ainesh.eazypg_owner;

public class RouterDetails {

    private String roomNo, brand, model, timeSinceInstallation, capacity, noOfAntenna, wirelessSpeed;

    public RouterDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String noOfAntenna, String wirelessSpeed) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.noOfAntenna = noOfAntenna;
        this.wirelessSpeed = wirelessSpeed;
    }

    public RouterDetails() {
        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        noOfAntenna = "";
        wirelessSpeed = "";
    }
}
