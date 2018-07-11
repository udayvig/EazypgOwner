package com.example.ainesh.eazypg_owner.Appliances;

public class FanDetails {

    public String roomNo, brand, model, timeSinceInstallation, noOfBlades;

    public FanDetails(String roomNo, String brand, String model, String timeSinceInstallation, String noOfBlades) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.noOfBlades = noOfBlades;
    }

    public FanDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        noOfBlades = "";
    }
}
