package com.example.ainesh.eazypg_owner;

public class PG {

    private String PGName, bio, Location, Ownername, PGContact, landmark, LastEntryTime, gender, MaxOccupancy, StaffCount, NoOfRooms, NoOfBathroom;

    public String getPGName() {
        return PGName;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return Location;
    }

    public String getOwnername() {
        return Ownername;
    }

    public String getPGContact() {
        return PGContact;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getLastEntryTime() {
        return LastEntryTime;
    }

    public String getGender() {
        return gender;
    }

    public String getMaxOccupancy() {
        return MaxOccupancy;
    }

    public String getStaffCount() {
        return StaffCount;
    }

    public String getNoOfRooms() {
        return NoOfRooms;
    }

    public String getNoOfBathroom() {
        return NoOfBathroom;
    }
    
    public PG(String PGName, String bio, String location, String ownername, String PGContact, String landmark, String lastEntryTime, String gender, String maxOccupancy, String staffCount, String noOfRooms, String noOfBathroom) {
        this.PGName = PGName;
        this.bio = bio;
        Location = location;
        Ownername = ownername;
        this.PGContact = PGContact;
        this.landmark = landmark;
        LastEntryTime = lastEntryTime;
        this.gender = gender;
        MaxOccupancy = maxOccupancy;
        StaffCount = staffCount;
        NoOfRooms = noOfRooms;
        NoOfBathroom = noOfBathroom;
    }

    public PG() {
        PGName = "";
        bio = "";
        Location = "";
        Ownername = "";
        PGContact = "";
        landmark = "";
        LastEntryTime = "";
        gender = "";
        MaxOccupancy = "";
        StaffCount = "";
        NoOfRooms = "";
        NoOfBathroom = "";
    }
}
