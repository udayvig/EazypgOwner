package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailTV {

    public String roomNo, brand, typeOfTV;

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getTypeOfTV() {
        return typeOfTV;
    }

    public ApplianceDetailTV(String roomNo, String brand, String typeOfTV) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.typeOfTV = typeOfTV;
    }

    public ApplianceDetailTV() {
    }
}
