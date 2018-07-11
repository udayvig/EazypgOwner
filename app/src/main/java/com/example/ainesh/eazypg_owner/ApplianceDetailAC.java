package com.example.ainesh.eazypg_owner;

public class ApplianceDetailAC {

    public String roomNo, brand, lastServiceDate;

    public ApplianceDetailAC() {

    }

    public ApplianceDetailAC(String roomNo, String brand, String lastServiceDate) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.lastServiceDate = lastServiceDate;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLastServiceDate(String lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

}
