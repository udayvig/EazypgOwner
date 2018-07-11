package com.example.ainesh.eazypg_owner.Appliances;

public class WashingMachineDetails {

    public String roomNo, brand, model, timeSinceInstallation, capacity, inputPower, starRating, typeOfMachine;

    public WashingMachineDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String inputPower, String starRating, String typeOfMachine) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.inputPower = inputPower;
        this.starRating = starRating;
        this.typeOfMachine = typeOfMachine;
    }

    public WashingMachineDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        inputPower = "";
        starRating = "";
        typeOfMachine = "";
    }
}
