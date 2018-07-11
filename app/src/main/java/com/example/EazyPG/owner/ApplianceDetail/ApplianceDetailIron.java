package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailIron {

    public String roomNo, brand, timeSinceInstallation;

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getTimeSinceInstallation() {
        return timeSinceInstallation;
    }

    public ApplianceDetailIron(String roomNo, String brand, String timeSinceInstallation) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.timeSinceInstallation = timeSinceInstallation;
    }

    public ApplianceDetailIron() {
    }
}
