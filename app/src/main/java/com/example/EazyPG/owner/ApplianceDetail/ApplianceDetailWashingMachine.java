package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailWashingMachine {

    public String roomNo, brand, typeOfMachine;

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getTypeOfMachine() {
        return typeOfMachine;
    }

    public ApplianceDetailWashingMachine(String roomNo, String brand, String typeOfMachine) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.typeOfMachine = typeOfMachine;
    }

    public ApplianceDetailWashingMachine() {
    }
}
