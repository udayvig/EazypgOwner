package com.example.EazyPG.owner.DetailsClasses;

public class PG {

    public String pgName, bio, location, ownername, pgContact, landmark, lastEntryTime, gender, maxOccupancy, staffCount, noOfRooms, noOfBathroom, rentDueDate, billDueDate, electricityUnitCost;

    public PG(String PGName, String bio, String location, String ownername, String PGContact, String landmark, String lastEntryTime, String gender, String maxOccupancy, String staffCount, String noOfRooms, String noOfBathroom, String rentDueDate, String billDueDate, String electricityUnitCost) {
        this.pgName = PGName;
        this.bio = bio;
        this.location = location;
        this.ownername = ownername;
        this.pgContact = PGContact;
        this.landmark = landmark;
        this.lastEntryTime = lastEntryTime;
        this.gender = gender;
        this.maxOccupancy = maxOccupancy;
        this.staffCount = staffCount;
        this.noOfRooms = noOfRooms;
        this.noOfBathroom = noOfBathroom;
        this.rentDueDate = rentDueDate;
        this.billDueDate = billDueDate;
        this.electricityUnitCost = electricityUnitCost;
    }

    public PG() {
        pgName = "";
        bio = "";
        location = "";
        ownername = "";
        pgContact = "";
        landmark = "";
        lastEntryTime = "";
        gender = "";
        maxOccupancy = "";
        staffCount = "";
        noOfRooms = "";
        noOfBathroom = "";
        rentDueDate = "";
        billDueDate = "";
        electricityUnitCost = "";
    }
}
