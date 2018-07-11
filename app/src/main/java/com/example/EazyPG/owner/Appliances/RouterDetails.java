package com.example.EazyPG.owner.Appliances;

public class RouterDetails {

    public String roomNo, brand, model, timeSinceInstallation, noOfAntenna, wirelessSpeed;

    public RouterDetails(String roomNo, String brand, String model, String timeSinceInstallation, String noOfAntenna, String wirelessSpeed) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.noOfAntenna = noOfAntenna;
        this.wirelessSpeed = wirelessSpeed;
    }

    public RouterDetails() {
        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        noOfAntenna = "";
        wirelessSpeed = "";
    }
}
