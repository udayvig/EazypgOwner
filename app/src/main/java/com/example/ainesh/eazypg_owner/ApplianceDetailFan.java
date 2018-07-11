package com.example.ainesh.eazypg_owner;

public class ApplianceDetailFan {

    public String roomNo, brand, timeSinceInstallation;

    public ApplianceDetailFan() {
    }

    public ApplianceDetailFan(String roomNo, String brand, String timeSinceInstallation) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.timeSinceInstallation = timeSinceInstallation;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getTimeSinceInstallation() {
        return timeSinceInstallation;
    }
}
