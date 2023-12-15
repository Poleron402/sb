package com.example.chilljava.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ChillJavaDB_Impl extends ChillJavaDB {
  private volatile ChillJavaDAO _chillJavaDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `UserTable` (`mUserId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `mUsername` TEXT, `mPassword` TEXT, `stars` INTEGER NOT NULL, `isadmin` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `MenuTable` (`itemId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemName` TEXT, `numShots` INTEGER NOT NULL, `canBeIced` INTEGER NOT NULL, `price` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `OrdersTable` (`orderId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `customerId` INTEGER NOT NULL, `itemIds` TEXT, FOREIGN KEY(`customerId`) REFERENCES `UserTable`(`mUserId`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_OrdersTable_customerId` ON `OrdersTable` (`customerId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0b68c2485531702153cc4e862a7989eb')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `UserTable`");
        db.execSQL("DROP TABLE IF EXISTS `MenuTable`");
        db.execSQL("DROP TABLE IF EXISTS `OrdersTable`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUserTable = new HashMap<String, TableInfo.Column>(5);
        _columnsUserTable.put("mUserId", new TableInfo.Column("mUserId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("mUsername", new TableInfo.Column("mUsername", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("mPassword", new TableInfo.Column("mPassword", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("stars", new TableInfo.Column("stars", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("isadmin", new TableInfo.Column("isadmin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTable = new TableInfo("UserTable", _columnsUserTable, _foreignKeysUserTable, _indicesUserTable);
        final TableInfo _existingUserTable = TableInfo.read(db, "UserTable");
        if (!_infoUserTable.equals(_existingUserTable)) {
          return new RoomOpenHelper.ValidationResult(false, "UserTable(com.example.chilljava.db.User).\n"
                  + " Expected:\n" + _infoUserTable + "\n"
                  + " Found:\n" + _existingUserTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMenuTable = new HashMap<String, TableInfo.Column>(5);
        _columnsMenuTable.put("itemId", new TableInfo.Column("itemId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuTable.put("itemName", new TableInfo.Column("itemName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuTable.put("numShots", new TableInfo.Column("numShots", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuTable.put("canBeIced", new TableInfo.Column("canBeIced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuTable.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMenuTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMenuTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMenuTable = new TableInfo("MenuTable", _columnsMenuTable, _foreignKeysMenuTable, _indicesMenuTable);
        final TableInfo _existingMenuTable = TableInfo.read(db, "MenuTable");
        if (!_infoMenuTable.equals(_existingMenuTable)) {
          return new RoomOpenHelper.ValidationResult(false, "MenuTable(com.example.chilljava.db.Menu).\n"
                  + " Expected:\n" + _infoMenuTable + "\n"
                  + " Found:\n" + _existingMenuTable);
        }
        final HashMap<String, TableInfo.Column> _columnsOrdersTable = new HashMap<String, TableInfo.Column>(3);
        _columnsOrdersTable.put("orderId", new TableInfo.Column("orderId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrdersTable.put("customerId", new TableInfo.Column("customerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrdersTable.put("itemIds", new TableInfo.Column("itemIds", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOrdersTable = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysOrdersTable.add(new TableInfo.ForeignKey("UserTable", "NO ACTION", "NO ACTION", Arrays.asList("customerId"), Arrays.asList("mUserId")));
        final HashSet<TableInfo.Index> _indicesOrdersTable = new HashSet<TableInfo.Index>(1);
        _indicesOrdersTable.add(new TableInfo.Index("index_OrdersTable_customerId", false, Arrays.asList("customerId"), Arrays.asList("ASC")));
        final TableInfo _infoOrdersTable = new TableInfo("OrdersTable", _columnsOrdersTable, _foreignKeysOrdersTable, _indicesOrdersTable);
        final TableInfo _existingOrdersTable = TableInfo.read(db, "OrdersTable");
        if (!_infoOrdersTable.equals(_existingOrdersTable)) {
          return new RoomOpenHelper.ValidationResult(false, "OrdersTable(com.example.chilljava.db.Orders).\n"
                  + " Expected:\n" + _infoOrdersTable + "\n"
                  + " Found:\n" + _existingOrdersTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "0b68c2485531702153cc4e862a7989eb", "a84fbd74f9ea15f8577892e50a6aae06");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "UserTable","MenuTable","OrdersTable");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `UserTable`");
      _db.execSQL("DELETE FROM `MenuTable`");
      _db.execSQL("DELETE FROM `OrdersTable`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ChillJavaDAO.class, ChillJavaDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ChillJavaDAO getChillJavaDAO() {
    if (_chillJavaDAO != null) {
      return _chillJavaDAO;
    } else {
      synchronized(this) {
        if(_chillJavaDAO == null) {
          _chillJavaDAO = new ChillJavaDAO_Impl(this);
        }
        return _chillJavaDAO;
      }
    }
  }
}
