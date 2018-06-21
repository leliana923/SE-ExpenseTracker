package com.example.yoong.se_expensetracker;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EntryDao {

    @Query("SELECT * FROM entry")
    List<Entry> getAllEntries();

    @Query("SELECT * FROM entry WHERE date LIKE :date")
    List<Entry> getAllByDates(String date);

    @Insert
    void insertAll(Entry... entries);

    @Delete
    void delete(Entry entry);

    @Update
    void updateEntry(Entry entry);
}
