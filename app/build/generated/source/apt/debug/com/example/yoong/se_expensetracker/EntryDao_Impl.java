package com.example.yoong.se_expensetracker;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class EntryDao_Impl implements EntryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEntry;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfEntry;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfEntry;

  public EntryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntry = new EntityInsertionAdapter<Entry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Entry`(`uid`,`category`,`date`,`symbol`,`amount`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Entry value) {
        stmt.bindLong(1, value.getUid());
        if (value.getCategory() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCategory());
        }
        if (value.getDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate());
        }
        if (value.getSymbol() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSymbol());
        }
        stmt.bindDouble(5, value.getAmount());
      }
    };
    this.__deletionAdapterOfEntry = new EntityDeletionOrUpdateAdapter<Entry>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Entry` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Entry value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__updateAdapterOfEntry = new EntityDeletionOrUpdateAdapter<Entry>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Entry` SET `uid` = ?,`category` = ?,`date` = ?,`symbol` = ?,`amount` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Entry value) {
        stmt.bindLong(1, value.getUid());
        if (value.getCategory() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCategory());
        }
        if (value.getDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate());
        }
        if (value.getSymbol() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSymbol());
        }
        stmt.bindDouble(5, value.getAmount());
        stmt.bindLong(6, value.getUid());
      }
    };
  }

  @Override
  public void insertAll(Entry... entries) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEntry.insert(entries);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Entry entry) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfEntry.handle(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateEntry(Entry entry) {
    __db.beginTransaction();
    try {
      __updateAdapterOfEntry.handle(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Entry> getAllEntries() {
    final String _sql = "SELECT * FROM entry";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfSymbol = _cursor.getColumnIndexOrThrow("symbol");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
      final List<Entry> _result = new ArrayList<Entry>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Entry _item;
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        final String _tmpSymbol;
        _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        _item = new Entry(_tmpCategory,_tmpDate,_tmpSymbol,_tmpAmount);
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Entry> getAllByDates(String date) {
    final String _sql = "SELECT * FROM entry WHERE date LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfSymbol = _cursor.getColumnIndexOrThrow("symbol");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
      final List<Entry> _result = new ArrayList<Entry>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Entry _item;
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        final String _tmpSymbol;
        _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        _item = new Entry(_tmpCategory,_tmpDate,_tmpSymbol,_tmpAmount);
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
