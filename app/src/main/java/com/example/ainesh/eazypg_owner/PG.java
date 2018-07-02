package com.example.ainesh.eazypg_owner;

public class PG {

    public String pgName, bio, location, ownername, pgContact, landmark, lastEntryTime, gender, maxOccupancy, staffCount, noOfRooms, noOfBathroom;

    /*public String getPGName() {
        return pgName;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getOwnername() {
        return ownername;
    }

    public String getpgContact() {
        return pgContact;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getLastEntryTime() {
        return lastEntryTime;
    }

    public String getGender() {
        return gender;
    }

    public String getMaxOccupancy() {
        return maxOccupancy;
    }

    public String getStaffCount() {
        return staffCount;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public String getNoOfBathroom() {
        return noOfBathroom;
    }
    */
    public PG(String PGName, String bio, String location, String ownername, String PGContact, String landmark, String lastEntryTime, String gender, String maxOccupancy, String staffCount, String noOfRooms, String noOfBathroom) {
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
    }
}
