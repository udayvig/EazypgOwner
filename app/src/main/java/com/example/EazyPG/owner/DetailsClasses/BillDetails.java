package com.example.EazyPG.owner.DetailsClasses;

public class BillDetails {
    String billId, category, amount;
    boolean paidOrUnpaid;

    public BillDetails(String billId, String category, String amount, boolean paidOrUnpaid) {
        this.billId = billId;
        this.category = category;
        this.amount = amount;
        this.paidOrUnpaid = paidOrUnpaid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public boolean isPaidOrUnpaid() {
        return paidOrUnpaid;
    }

    public void setPaidOrUnpaid(boolean paidOrUnpaid) {
        this.paidOrUnpaid = paidOrUnpaid;
    }
}
