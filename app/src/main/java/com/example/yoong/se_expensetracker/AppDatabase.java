package com.example.yoong.se_expensetracker;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Entry.class, Currency.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();
    public abstract CurrencyDao currencyDao();
}
