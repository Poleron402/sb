package com.example.chilljava.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = ChillJavaDB.MENU_TABLE)
public class Menu {
    @PrimaryKey(autoGenerate = true)
    private int itemId;
    private String itemName;
    private int numShots;
    private boolean canBeIced;
    private double price;

    public Menu(String itemName, int numShots, boolean canBeIced, double price) {
        this.itemName = itemName;
        this.numShots = numShots;
        this.canBeIced = canBeIced;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int menuId) {
        this.itemId = menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getNumShots() {
        return numShots;
    }

    public void setNumShots(int numShots) {
        this.numShots = numShots;
    }

    public boolean isCanBeIced() {
        return canBeIced;
    }

    public void setCanBeIced(boolean canBeIced) {
        this.canBeIced = canBeIced;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "itemName='" + itemName + '\'' +
                ", numShots=" + numShots +
                ", canBeIced=" + canBeIced +
                ", price=" + price +
                '}';
    }
}
