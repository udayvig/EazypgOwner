package com.example.EazyPG.owner.DetailsClasses;

public class GuestDetails {

    public String guestName, guestContact, guestToTime, guestFromTime, guestImageName, guestId, date;
    public String tenantName, tenantPhone;

    public GuestDetails(String guestName, String guestContact, String guestToTime, String guestFromTime, String guestImageName, String guestId, String date) {
        this.guestName = guestName;
        this.guestContact = guestContact;
        this.guestToTime = guestToTime;
        this.guestFromTime = guestFromTime;
        this.guestImageName = guestImageName;
        this.guestId = guestId;
        this.date = date;
    }

    public GuestDetails(String guestName, String guestContact, String guestToTime, String guestFromTime, String guestImageName, String tenantName, String tenantPhone, String guestId, String date) {
        this.guestName = guestName;
        this.guestContact = guestContact;
        this.guestToTime = guestToTime;
        this.guestFromTime = guestFromTime;
        this.guestImageName = guestImageName;
        this.tenantName = tenantName;
        this.tenantPhone = tenantPhone;
        this.guestId = guestId;
        this.date = date;
    }

    public GuestDetails() {
    }
}
