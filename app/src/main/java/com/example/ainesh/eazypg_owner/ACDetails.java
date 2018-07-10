package com.example.ainesh.eazypg_owner;

public class ACDetails {

    String roomNo, brand, model, capacity, lastServiceData, starRating, typeOfRating;

    public ACDetails(String roomNo, String brand, String model, String capacity, String lastServiceData, String starRating, String typeOfRating) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.lastServiceData = lastServiceData;
        this.starRating = starRating;
        this.typeOfRating = typeOfRating;
    }

    public ACDetails() {
        roomNo = "";
        brand = "";
        model = "";
        capacity = "";
        lastServiceData = "";
        starRating = "";
        typeOfRating = "";

    }


}
