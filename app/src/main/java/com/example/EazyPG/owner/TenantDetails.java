package com.example.EazyPG.owner;

public class TenantDetails {

    String tenantId, tenantName, tenantPhone, tenantEmail, tenantRoom, tenantDateOfJoining, tenantRentAmount;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }

    public String getTenantRoom() {
        return tenantRoom;
    }

    public void setTenantRoom(String tenantRoom) {
        this.tenantRoom = tenantRoom;
    }

    public String getTenantDateOfJoining() {
        return tenantDateOfJoining;
    }

    public void setTenantDateOfJoining(String tenantDateOfJoining) {
        this.tenantDateOfJoining = tenantDateOfJoining;
    }

    public String getTenantRentAmount() {
        return tenantRentAmount;
    }

    public void setTenantRentAmount(String tenantRentAmount) {
        this.tenantRentAmount = tenantRentAmount;
    }

    public TenantDetails(String tenantId, String tenantName, String tenantPhone, String tenantEmail, String tenantRoom, String tenantDateOfJoining, String tenantRentAmount) {
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.tenantPhone = tenantPhone;
        this.tenantEmail = tenantEmail;
        this.tenantRoom = tenantRoom;
        this.tenantDateOfJoining = tenantDateOfJoining;
        this.tenantRentAmount = tenantRentAmount;
    }

    public TenantDetails() {

    }
}
