package com.example.chilljava.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Menu.class, Orders.class}, version=3)
public abstract class ChillJavaDB extends RoomDatabase {
    public static final String DB_NAME = "ChillJavaDB";
    public static final String USER_TABLE = "UserTable";
    public static final String MENU_TABLE = "MenuTable";
    public static final String ORDERS_TABLE = "OrdersTable";
    public abstract ChillJavaDAO getChillJavaDAO();


}
