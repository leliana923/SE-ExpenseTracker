package com.example.yoong.se_expensetracker;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Entry {

    @Ignore
    public Entry(){

    }

    public Entry(String category, String date, String symbol, double amount) {
        this.category = category;
        this.date = date;
        this.symbol = symbol;
        this.amount = amount;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "symbol")
    private String symbol;

    @ColumnInfo(name = "amount")
    private double amount;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
