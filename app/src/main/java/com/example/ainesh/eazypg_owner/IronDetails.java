package com.example.ainesh.eazypg_owner;

public class IronDetails {

    public String roomNo, brand, model, timeSinceInstallation, inputPower;

    public IronDetails(String roomNo, String brand, String model, String timeSinceInstallation, String inputPower) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.inputPower = inputPower;
    }

    public IronDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        inputPower = "";
    }
}
