package com.example.yoong.se_expensetracker;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface CurrencyDao {

    @Query("SELECT * FROM currency LIMIT 1")
    Currency getSelectedCurrency();

    @Insert
    void insertCurrency(Currency currency);

    @Delete
    void deleteCurrency(Currency currency);

}
