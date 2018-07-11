package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailMicrowave {

    public String roomNo, brand, capacity;

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getCapacity() {
        return capacity;
    }

    public ApplianceDetailMicrowave(String roomNo, String brand, String capacity) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.capacity = capacity;
    }

    public ApplianceDetailMicrowave() {
    }
}
