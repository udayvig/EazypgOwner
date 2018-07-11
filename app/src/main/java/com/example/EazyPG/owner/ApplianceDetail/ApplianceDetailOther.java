package com.example.EazyPG.owner.ApplianceDetail;

public class ApplianceDetailOther {

    public String roomNo, applianceName, brand;

    public String getRoomNo() {
        return roomNo;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public String getBrand() {
        return brand;
    }

    public ApplianceDetailOther(String roomNo, String applianceName, String brand) {
        this.roomNo = roomNo;
        this.applianceName = applianceName;
        this.brand = brand;
    }

    public ApplianceDetailOther() {
    }
}
