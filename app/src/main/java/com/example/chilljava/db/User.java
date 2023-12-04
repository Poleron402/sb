package com.example.chilljava.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = ChillJavaDB.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int mUserId;
    private String mUsername;
    private String mPassword;
    private int stars;
    private String isadmin;

    public User(String mUsername, String mPassword, int stars, String isadmin) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.stars = stars;
        this.isadmin = isadmin;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public int getUserId() {
        return mUserId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
