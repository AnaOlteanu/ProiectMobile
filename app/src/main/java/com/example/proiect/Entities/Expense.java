package com.example.proiect.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String expenseTitle;
    @ColumnInfo(name = "details")
    private String expenseDetails;
    private float amount;
    private int userId;

    public Expense() {
    }

    public Expense(String expenseTitle, String expenseDetails, float amount) {
        this.expenseTitle = expenseTitle;
        this.expenseDetails = expenseDetails;
        this.amount = amount;
    }

    public Expense(int id, String expenseTitle, String expenseDetails, float amount, int userId) {
        this.id = id;
        this.expenseTitle = expenseTitle;
        this.expenseDetails = expenseDetails;
        this.amount = amount;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public void setExpenseTitle(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }

    public String getExpenseDetails() {
        return expenseDetails;
    }

    public void setExpenseDetails(String expenseDetails) {
        this.expenseDetails = expenseDetails;
    }
}
