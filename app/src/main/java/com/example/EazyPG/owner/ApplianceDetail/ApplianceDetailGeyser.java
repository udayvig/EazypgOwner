package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailGeyser {

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

    public ApplianceDetailGeyser(String roomNo, String brand, String capacity) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.capacity = capacity;
    }

    public ApplianceDetailGeyser() {
    }
}
