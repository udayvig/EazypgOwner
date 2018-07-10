package com.example.ainesh.eazypg_owner;

public class CCTVDetails {

    private String roomNo, brand, model, timeSinceInstallation, nightVision, noOfChannels, recordingResolution;

    public CCTVDetails(String roomNo, String brand, String model, String timeSinceInstallation, String nightVision, String noOfChannels, String recordingResolution) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.nightVision = nightVision;
        this.noOfChannels = noOfChannels;
        this.recordingResolution = recordingResolution;
    }

    public CCTVDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        nightVision = "";
        noOfChannels = "";
        recordingResolution = "";
    }
}
