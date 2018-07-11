package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailHeater {

    public String roomNo, brand, inputPower;

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getInputPower() {
        return inputPower;
    }

    public ApplianceDetailHeater(String roomNo, String brand, String inputPower) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.inputPower = inputPower;
    }

    public ApplianceDetailHeater() {
    }
}
