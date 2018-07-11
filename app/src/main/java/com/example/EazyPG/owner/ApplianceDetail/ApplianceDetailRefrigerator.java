package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailRefrigerator {

    public String roomNo, brand, typeOfRefrigerator;

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getTypeOfRefrigerator() {
        return typeOfRefrigerator;
    }

    public ApplianceDetailRefrigerator(String roomNo, String brand, String typeOfRefrigerator) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.typeOfRefrigerator = typeOfRefrigerator;
    }

    public ApplianceDetailRefrigerator() {
    }
}
