package com.example.EazyPG.owner.Appliances;

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

    public String getRoomNo() {
        return roomNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public String getStarRating() {
        return starRating;
    }

    public String getTypeOfRating() {
        return typeOfRating;
    }
}
