package com.example.yoong.se_expensetracker;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Currency {

    @Ignore
    public Currency() {
    }

    public Currency(String currency){
        this.currency = currency;
    }

    @PrimaryKey(autoGenerate = true)
    private int cid;

    @ColumnInfo(name = "currency")
    private String currency;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
