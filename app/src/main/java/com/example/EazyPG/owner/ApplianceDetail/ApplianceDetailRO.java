package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailRO {

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

    public ApplianceDetailRO(String roomNo, String brand, String timeSinceInstallation) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.timeSinceInstallation = timeSinceInstallation;
    }

    public ApplianceDetailRO() {
    }
}
