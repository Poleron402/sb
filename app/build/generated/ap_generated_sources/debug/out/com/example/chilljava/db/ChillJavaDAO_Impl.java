package com.example.chilljava.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ChillJavaDAO_Impl implements ChillJavaDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityInsertionAdapter<Menu> __insertionAdapterOfMenu;

  private final EntityInsertionAdapter<Orders> __insertionAdapterOfOrders;

  private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<Menu> __deletionAdapterOfMenu;

  private final EntityDeletionOrUpdateAdapter<Orders> __deletionAdapterOfOrders;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<Menu> __updateAdapterOfMenu;

  private final EntityDeletionOrUpdateAdapter<Orders> __updateAdapterOfOrders;

  public ChillJavaDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `UserTable` (`mUserId`,`mUsername`,`mPassword`,`stars`,`isadmin`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.getUserId());
        if (entity.getUsername() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUsername());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPassword());
        }
        statement.bindLong(4, entity.getStars());
        if (entity.getIsadmin() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getIsadmin());
        }
      }
    };
    this.__insertionAdapterOfMenu = new EntityInsertionAdapter<Menu>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `MenuTable` (`itemId`,`itemName`,`numShots`,`canBeIced`,`price`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Menu entity) {
        statement.bindLong(1, entity.getItemId());
        if (entity.getItemName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getItemName());
        }
        statement.bindLong(3, entity.getNumShots());
        final int _tmp = entity.isCanBeIced() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindDouble(5, entity.getPrice());
      }
    };
    this.__insertionAdapterOfOrders = new EntityInsertionAdapter<Orders>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `OrdersTable` (`orderId`,`customerId`,`itemIds`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Orders entity) {
        statement.bindLong(1, entity.getOrderId());
        statement.bindLong(2, entity.getCustomerId());
        if (entity.getItemIds() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getItemIds());
        }
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `UserTable` WHERE `mUserId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.getUserId());
      }
    };
    this.__deletionAdapterOfMenu = new EntityDeletionOrUpdateAdapter<Menu>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `MenuTable` WHERE `itemId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Menu entity) {
        statement.bindLong(1, entity.getItemId());
      }
    };
    this.__deletionAdapterOfOrders = new EntityDeletionOrUpdateAdapter<Orders>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `OrdersTable` WHERE `orderId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Orders entity) {
        statement.bindLong(1, entity.getOrderId());
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `UserTable` SET `mUserId` = ?,`mUsername` = ?,`mPassword` = ?,`stars` = ?,`isadmin` = ? WHERE `mUserId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.getUserId());
        if (entity.getUsername() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUsername());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPassword());
        }
        statement.bindLong(4, entity.getStars());
        if (entity.getIsadmin() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getIsadmin());
        }
        statement.bindLong(6, entity.getUserId());
      }
    };
    this.__updateAdapterOfMenu = new EntityDeletionOrUpdateAdapter<Menu>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `MenuTable` SET `itemId` = ?,`itemName` = ?,`numShots` = ?,`canBeIced` = ?,`price` = ? WHERE `itemId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Menu entity) {
        statement.bindLong(1, entity.getItemId());
        if (entity.getItemName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getItemName());
        }
        statement.bindLong(3, entity.getNumShots());
        final int _tmp = entity.isCanBeIced() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindDouble(5, entity.getPrice());
        statement.bindLong(6, entity.getItemId());
      }
    };
    this.__updateAdapterOfOrders = new EntityDeletionOrUpdateAdapter<Orders>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `OrdersTable` SET `orderId` = ?,`customerId` = ?,`itemIds` = ? WHERE `orderId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Orders entity) {
        statement.bindLong(1, entity.getOrderId());
        statement.bindLong(2, entity.getCustomerId());
        if (entity.getItemIds() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getItemIds());
        }
        statement.bindLong(4, entity.getOrderId());
      }
    };
  }

  @Override
  public void insert(final User... users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final Menu... items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMenu.insert(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final Orders... orders) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrders.insert(orders);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Menu item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMenu.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Orders order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfOrders.handle(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final User... users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUser.handleMultiple(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Menu... items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMenu.handleMultiple(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Orders... orders) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfOrders.handleMultiple(orders);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<User> getAllUser() {
    final String _sql = "select * from UserTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "mUserId");
      final int _cursorIndexOfMUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "mUsername");
      final int _cursorIndexOfMPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "mPassword");
      final int _cursorIndexOfStars = CursorUtil.getColumnIndexOrThrow(_cursor, "stars");
      final int _cursorIndexOfIsadmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isadmin");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final User _item;
        final String _tmpMUsername;
        if (_cursor.isNull(_cursorIndexOfMUsername)) {
          _tmpMUsername = null;
        } else {
          _tmpMUsername = _cursor.getString(_cursorIndexOfMUsername);
        }
        final String _tmpMPassword;
        if (_cursor.isNull(_cursorIndexOfMPassword)) {
          _tmpMPassword = null;
        } else {
          _tmpMPassword = _cursor.getString(_cursorIndexOfMPassword);
        }
        final int _tmpStars;
        _tmpStars = _cursor.getInt(_cursorIndexOfStars);
        final String _tmpIsadmin;
        if (_cursor.isNull(_cursorIndexOfIsadmin)) {
          _tmpIsadmin = null;
        } else {
          _tmpIsadmin = _cursor.getString(_cursorIndexOfIsadmin);
        }
        _item = new User(_tmpMUsername,_tmpMPassword,_tmpStars,_tmpIsadmin);
        final int _tmpMUserId;
        _tmpMUserId = _cursor.getInt(_cursorIndexOfMUserId);
        _item.setUserId(_tmpMUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByUsername(final String username) {
    final String _sql = "select * from UserTable where mUsername = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "mUserId");
      final int _cursorIndexOfMUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "mUsername");
      final int _cursorIndexOfMPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "mPassword");
      final int _cursorIndexOfStars = CursorUtil.getColumnIndexOrThrow(_cursor, "stars");
      final int _cursorIndexOfIsadmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isadmin");
      final User _result;
      if (_cursor.moveToFirst()) {
        final String _tmpMUsername;
        if (_cursor.isNull(_cursorIndexOfMUsername)) {
          _tmpMUsername = null;
        } else {
          _tmpMUsername = _cursor.getString(_cursorIndexOfMUsername);
        }
        final String _tmpMPassword;
        if (_cursor.isNull(_cursorIndexOfMPassword)) {
          _tmpMPassword = null;
        } else {
          _tmpMPassword = _cursor.getString(_cursorIndexOfMPassword);
        }
        final int _tmpStars;
        _tmpStars = _cursor.getInt(_cursorIndexOfStars);
        final String _tmpIsadmin;
        if (_cursor.isNull(_cursorIndexOfIsadmin)) {
          _tmpIsadmin = null;
        } else {
          _tmpIsadmin = _cursor.getString(_cursorIndexOfIsadmin);
        }
        _result = new User(_tmpMUsername,_tmpMPassword,_tmpStars,_tmpIsadmin);
        final int _tmpMUserId;
        _tmpMUserId = _cursor.getInt(_cursorIndexOfMUserId);
        _result.setUserId(_tmpMUserId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserById(final int userId) {
    final String _sql = "select * from UserTable where mUserId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "mUserId");
      final int _cursorIndexOfMUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "mUsername");
      final int _cursorIndexOfMPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "mPassword");
      final int _cursorIndexOfStars = CursorUtil.getColumnIndexOrThrow(_cursor, "stars");
      final int _cursorIndexOfIsadmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isadmin");
      final User _result;
      if (_cursor.moveToFirst()) {
        final String _tmpMUsername;
        if (_cursor.isNull(_cursorIndexOfMUsername)) {
          _tmpMUsername = null;
        } else {
          _tmpMUsername = _cursor.getString(_cursorIndexOfMUsername);
        }
        final String _tmpMPassword;
        if (_cursor.isNull(_cursorIndexOfMPassword)) {
          _tmpMPassword = null;
        } else {
          _tmpMPassword = _cursor.getString(_cursorIndexOfMPassword);
        }
        final int _tmpStars;
        _tmpStars = _cursor.getInt(_cursorIndexOfStars);
        final String _tmpIsadmin;
        if (_cursor.isNull(_cursorIndexOfIsadmin)) {
          _tmpIsadmin = null;
        } else {
          _tmpIsadmin = _cursor.getString(_cursorIndexOfIsadmin);
        }
        _result = new User(_tmpMUsername,_tmpMPassword,_tmpStars,_tmpIsadmin);
        final int _tmpMUserId;
        _tmpMUserId = _cursor.getInt(_cursorIndexOfMUserId);
        _result.setUserId(_tmpMUserId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Menu> getAllItems() {
    final String _sql = "select * from MenuTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfNumShots = CursorUtil.getColumnIndexOrThrow(_cursor, "numShots");
      final int _cursorIndexOfCanBeIced = CursorUtil.getColumnIndexOrThrow(_cursor, "canBeIced");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final List<Menu> _result = new ArrayList<Menu>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Menu _item;
        final String _tmpItemName;
        if (_cursor.isNull(_cursorIndexOfItemName)) {
          _tmpItemName = null;
        } else {
          _tmpItemName = _cursor.getString(_cursorIndexOfItemName);
        }
        final int _tmpNumShots;
        _tmpNumShots = _cursor.getInt(_cursorIndexOfNumShots);
        final boolean _tmpCanBeIced;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCanBeIced);
        _tmpCanBeIced = _tmp != 0;
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item = new Menu(_tmpItemName,_tmpNumShots,_tmpCanBeIced,_tmpPrice);
        final int _tmpItemId;
        _tmpItemId = _cursor.getInt(_cursorIndexOfItemId);
        _item.setItemId(_tmpItemId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Menu> getItemByName(final String itemName) {
    final String _sql = "select * from MenuTable where itemName like ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (itemName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, itemName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfNumShots = CursorUtil.getColumnIndexOrThrow(_cursor, "numShots");
      final int _cursorIndexOfCanBeIced = CursorUtil.getColumnIndexOrThrow(_cursor, "canBeIced");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final List<Menu> _result = new ArrayList<Menu>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Menu _item;
        final String _tmpItemName;
        if (_cursor.isNull(_cursorIndexOfItemName)) {
          _tmpItemName = null;
        } else {
          _tmpItemName = _cursor.getString(_cursorIndexOfItemName);
        }
        final int _tmpNumShots;
        _tmpNumShots = _cursor.getInt(_cursorIndexOfNumShots);
        final boolean _tmpCanBeIced;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCanBeIced);
        _tmpCanBeIced = _tmp != 0;
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item = new Menu(_tmpItemName,_tmpNumShots,_tmpCanBeIced,_tmpPrice);
        final int _tmpItemId;
        _tmpItemId = _cursor.getInt(_cursorIndexOfItemId);
        _item.setItemId(_tmpItemId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Menu getItemById(final int itemId) {
    final String _sql = "select * from MenuTable where itemId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
      final int _cursorIndexOfNumShots = CursorUtil.getColumnIndexOrThrow(_cursor, "numShots");
      final int _cursorIndexOfCanBeIced = CursorUtil.getColumnIndexOrThrow(_cursor, "canBeIced");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final Menu _result;
      if (_cursor.moveToFirst()) {
        final String _tmpItemName;
        if (_cursor.isNull(_cursorIndexOfItemName)) {
          _tmpItemName = null;
        } else {
          _tmpItemName = _cursor.getString(_cursorIndexOfItemName);
        }
        final int _tmpNumShots;
        _tmpNumShots = _cursor.getInt(_cursorIndexOfNumShots);
        final boolean _tmpCanBeIced;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfCanBeIced);
        _tmpCanBeIced = _tmp != 0;
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _result = new Menu(_tmpItemName,_tmpNumShots,_tmpCanBeIced,_tmpPrice);
        final int _tmpItemId;
        _tmpItemId = _cursor.getInt(_cursorIndexOfItemId);
        _result.setItemId(_tmpItemId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Orders> getAllOrders() {
    final String _sql = "select * from OrdersTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
      final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
      final int _cursorIndexOfItemIds = CursorUtil.getColumnIndexOrThrow(_cursor, "itemIds");
      final List<Orders> _result = new ArrayList<Orders>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Orders _item;
        final int _tmpCustomerId;
        _tmpCustomerId = _cursor.getInt(_cursorIndexOfCustomerId);
        final String _tmpItemIds;
        if (_cursor.isNull(_cursorIndexOfItemIds)) {
          _tmpItemIds = null;
        } else {
          _tmpItemIds = _cursor.getString(_cursorIndexOfItemIds);
        }
        _item = new Orders(_tmpCustomerId,_tmpItemIds);
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Orders getOrderById(final int orderId) {
    final String _sql = "select * from OrdersTable where orderId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
      final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
      final int _cursorIndexOfItemIds = CursorUtil.getColumnIndexOrThrow(_cursor, "itemIds");
      final Orders _result;
      if (_cursor.moveToFirst()) {
        final int _tmpCustomerId;
        _tmpCustomerId = _cursor.getInt(_cursorIndexOfCustomerId);
        final String _tmpItemIds;
        if (_cursor.isNull(_cursorIndexOfItemIds)) {
          _tmpItemIds = null;
        } else {
          _tmpItemIds = _cursor.getString(_cursorIndexOfItemIds);
        }
        _result = new Orders(_tmpCustomerId,_tmpItemIds);
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _result.setOrderId(_tmpOrderId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Orders> getOrderByCustId(final int custId) {
    final String _sql = "select * from OrdersTable where customerId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, custId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
      final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
      final int _cursorIndexOfItemIds = CursorUtil.getColumnIndexOrThrow(_cursor, "itemIds");
      final List<Orders> _result = new ArrayList<Orders>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Orders _item;
        final int _tmpCustomerId;
        _tmpCustomerId = _cursor.getInt(_cursorIndexOfCustomerId);
        final String _tmpItemIds;
        if (_cursor.isNull(_cursorIndexOfItemIds)) {
          _tmpItemIds = null;
        } else {
          _tmpItemIds = _cursor.getString(_cursorIndexOfItemIds);
        }
        _item = new Orders(_tmpCustomerId,_tmpItemIds);
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
