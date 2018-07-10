package com.example.ainesh.eazypg_owner;

public class D2HDetails {

    public String roomNo, companyName, timeSinceInstallation;

    public D2HDetails(String roomNo, String companyName, String timeSinceInstallation) {
        this.roomNo = roomNo;
        this.companyName = companyName;
        this.timeSinceInstallation = timeSinceInstallation;
    }

    public D2HDetails() {

        roomNo = "";
        companyName = "";
        timeSinceInstallation = "";
    }
}
