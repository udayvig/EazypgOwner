package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailInduction {

    public String roomNo, brand, controlType;

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getControlType() {
        return controlType;
    }

    public ApplianceDetailInduction(String roomNo, String brand, String controlType) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.controlType = controlType;
    }

    public ApplianceDetailInduction() {
    }
}
