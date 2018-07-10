package com.example.ainesh.eazypg_owner;

public class ACDetails {

    public String roomNo, brand, model, capacity, lastServiceDate, starRating, typeOfRating;

    public ACDetails(String roomNo, String brand, String model, String capacity, String lastServiceDate, String starRating, String typeOfRating) {
        this.roomNo = roomNo;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.lastServiceDate = lastServiceDate;
        this.starRating = starRating;
        this.typeOfRating = typeOfRating;
    }

    public ACDetails() {
        roomNo = "";
        brand = "";
        model = "";
        capacity = "";
        lastServiceDate = "";
        starRating = "";
        typeOfRating = "";

    }


}
