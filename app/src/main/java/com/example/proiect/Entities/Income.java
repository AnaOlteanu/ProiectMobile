package com.example.proiect.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Income {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String incomeTitle;
    @ColumnInfo(name = "details")
    private String incomeDetails;
    private float amount;
    private int userId;

    public Income() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Income(int id, String incomeTitle, String incomeDetails, float amount, int userId) {
        this.id = id;
        this.incomeTitle = incomeTitle;
        this.incomeDetails = incomeDetails;
        this.amount = amount;
        this.userId = userId;
    }

    public Income(String incomeTitle, String incomeDetails, float amount) {
        this.incomeTitle = incomeTitle;
        this.incomeDetails = incomeDetails;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getIncomeTitle() {
        return incomeTitle;
    }

    public void setIncomeTitle(String incomeTitle) {
        this.incomeTitle = incomeTitle;
    }

    public String getIncomeDetails() {
        return incomeDetails;
    }


    public void setIncomeDetails(String incomeDetails) {
        this.incomeDetails = incomeDetails;
    }
}
