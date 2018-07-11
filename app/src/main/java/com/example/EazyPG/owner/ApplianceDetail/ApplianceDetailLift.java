package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailLift {

    public String brand, timeSinceInstallation, doorType;

    public ApplianceDetailLift(String brand, String timeSinceInstallation, String doorType) {
        this.brand = brand;
        this.timeSinceInstallation = timeSinceInstallation;
        this.doorType = doorType;
    }

    public ApplianceDetailLift() {
    }

    public String getBrand() {
        return brand;
    }

    public String getTimeSinceInstallation() {
        return timeSinceInstallation;
    }

    public String getDoorType() {
        return doorType;
    }
}
