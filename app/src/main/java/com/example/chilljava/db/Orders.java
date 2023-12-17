package com.example.chilljava.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = ChillJavaDB.ORDERS_TABLE)
public class Orders {
    @PrimaryKey(autoGenerate = true)
    private int orderId;
    private int customerId;
    private String itemIds;

    public Orders(int customerId, String itemIds) {
        this.customerId = customerId;
        this.itemIds = itemIds;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemId) {
        this.itemIds = itemId;
    }
}
