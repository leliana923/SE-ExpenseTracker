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

@SuppressWarnings("unchecked")
public class CurrencyDao_Impl implements CurrencyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCurrency;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfCurrency;

  public CurrencyDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCurrency = new EntityInsertionAdapter<Currency>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Currency`(`cid`,`currency`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Currency value) {
        stmt.bindLong(1, value.getCid());
        if (value.getCurrency() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCurrency());
        }
      }
    };
    this.__deletionAdapterOfCurrency = new EntityDeletionOrUpdateAdapter<Currency>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Currency` WHERE `cid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Currency value) {
        stmt.bindLong(1, value.getCid());
      }
    };
  }

  @Override
  public void insertCurrency(Currency currency) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCurrency.insert(currency);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCurrency(Currency currency) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfCurrency.handle(currency);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Currency getSelectedCurrency() {
    final String _sql = "SELECT * FROM currency LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCid = _cursor.getColumnIndexOrThrow("cid");
      final int _cursorIndexOfCurrency = _cursor.getColumnIndexOrThrow("currency");
      final Currency _result;
      if(_cursor.moveToFirst()) {
        final String _tmpCurrency;
        _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
        _result = new Currency(_tmpCurrency);
        final int _tmpCid;
        _tmpCid = _cursor.getInt(_cursorIndexOfCid);
        _result.setCid(_tmpCid);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
