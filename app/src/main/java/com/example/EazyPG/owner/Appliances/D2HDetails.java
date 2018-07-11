package com.example.EazyPG.owner.Appliances;

public class D2HDetails {

    public String roomNo, brand, timeSinceInstallation;

    public D2HDetails(String roomNo, String brand, String timeSinceInstallation) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.timeSinceInstallation = timeSinceInstallation;
    }

    public D2HDetails() {

        roomNo = "";
        brand = "";
        timeSinceInstallation = "";
    }
}
