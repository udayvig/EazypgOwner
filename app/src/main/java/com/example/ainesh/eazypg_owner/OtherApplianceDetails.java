package com.example.ainesh.eazypg_owner;

public class OtherApplianceDetails {

    String roomNo, applianceName, brand;

    private OtherApplianceDetails(String roomNo, String applianceName, String brand) {
        this.roomNo = roomNo;
        this.applianceName = applianceName;
        this.brand = brand;
    }

    public OtherApplianceDetails() {
        roomNo = "";
        applianceName = "";
        brand = "";
    }
}
