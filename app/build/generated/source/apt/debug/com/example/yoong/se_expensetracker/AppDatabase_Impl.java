package com.example.yoong.se_expensetracker;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile EntryDao _entryDao;

  private volatile CurrencyDao _currencyDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Entry` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `category` TEXT, `date` TEXT, `symbol` TEXT, `amount` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Currency` (`cid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `currency` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"a04fea9bfae29d274487b98e08be8757\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Entry`");
        _db.execSQL("DROP TABLE IF EXISTS `Currency`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsEntry = new HashMap<String, TableInfo.Column>(5);
        _columnsEntry.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsEntry.put("category", new TableInfo.Column("category", "TEXT", false, 0));
        _columnsEntry.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsEntry.put("symbol", new TableInfo.Column("symbol", "TEXT", false, 0));
        _columnsEntry.put("amount", new TableInfo.Column("amount", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEntry = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEntry = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEntry = new TableInfo("Entry", _columnsEntry, _foreignKeysEntry, _indicesEntry);
        final TableInfo _existingEntry = TableInfo.read(_db, "Entry");
        if (! _infoEntry.equals(_existingEntry)) {
          throw new IllegalStateException("Migration didn't properly handle Entry(com.example.yoong.se_expensetracker.Entry).\n"
                  + " Expected:\n" + _infoEntry + "\n"
                  + " Found:\n" + _existingEntry);
        }
        final HashMap<String, TableInfo.Column> _columnsCurrency = new HashMap<String, TableInfo.Column>(2);
        _columnsCurrency.put("cid", new TableInfo.Column("cid", "INTEGER", true, 1));
        _columnsCurrency.put("currency", new TableInfo.Column("currency", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCurrency = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCurrency = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCurrency = new TableInfo("Currency", _columnsCurrency, _foreignKeysCurrency, _indicesCurrency);
        final TableInfo _existingCurrency = TableInfo.read(_db, "Currency");
        if (! _infoCurrency.equals(_existingCurrency)) {
          throw new IllegalStateException("Migration didn't properly handle Currency(com.example.yoong.se_expensetracker.Currency).\n"
                  + " Expected:\n" + _infoCurrency + "\n"
                  + " Found:\n" + _existingCurrency);
        }
      }
    }, "a04fea9bfae29d274487b98e08be8757", "945386be55b7da4a6e069ad0dd3c430d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Entry","Currency");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Entry`");
      _db.execSQL("DELETE FROM `Currency`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public EntryDao entryDao() {
    if (_entryDao != null) {
      return _entryDao;
    } else {
      synchronized(this) {
        if(_entryDao == null) {
          _entryDao = new EntryDao_Impl(this);
        }
        return _entryDao;
      }
    }
  }

  @Override
  public CurrencyDao currencyDao() {
    if (_currencyDao != null) {
      return _currencyDao;
    } else {
      synchronized(this) {
        if(_currencyDao == null) {
          _currencyDao = new CurrencyDao_Impl(this);
        }
        return _currencyDao;
      }
    }
  }
}
