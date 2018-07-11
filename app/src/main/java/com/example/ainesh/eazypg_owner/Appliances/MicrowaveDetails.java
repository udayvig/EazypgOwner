package com.example.ainesh.eazypg_owner.Appliances;

public class MicrowaveDetails {

    public String roomNo, brand, model, timeSinceInstallation, capacity, controlType;

    public MicrowaveDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String controlType) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.controlType = controlType;
    }

    public MicrowaveDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        controlType = "";
    }
}
