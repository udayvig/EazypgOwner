package com.example.EazyPG.owner;

public class ExpenseDetails {
    String expenseId, amount, category, description, paidBy, paidTo, date;

    public ExpenseDetails(String expenseId, String amount, String category, String description, String paidBy, String paidTo, String date) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.date = date;
    }

    public ExpenseDetails() {
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getDate() {
        return date;
    }
}
