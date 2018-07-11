package com.example.ainesh.eazypg_owner.Appliances;

public class DishwasherDetails {

    public String roomNo, brand, model, timeSinceInstallation, capacity, typeOfDishwasher;

    public DishwasherDetails(String roomNo, String brand, String model, String timeSinceInstallation, String capacity, String typeOfDishwasher) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.timeSinceInstallation = timeSinceInstallation;
        this.capacity = capacity;
        this.typeOfDishwasher = typeOfDishwasher;
    }

    public DishwasherDetails() {

        roomNo = "";
        brand = "";
        model = "";
        timeSinceInstallation = "";
        capacity = "";
        typeOfDishwasher = "";
    }
}
