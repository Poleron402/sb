package com.example.chilljava.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version=1)
public abstract class ChillJavaDB extends RoomDatabase {
    public static final String DB_NAME = "ChillJavaDB";
    public static final String USER_TABLE = "UserTable";
    public abstract ChillJavaDAO getChillJavaDAO();
}
