package com.example.chilljava.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = ChillJavaDB.ORDERS_TABLE,
        foreignKeys = {@ForeignKey(entity = User.class,
                parentColumns = "mUserId",
                childColumns = "customerId"),
                @ForeignKey(entity=Menu.class,
                parentColumns = "itemId",
                childColumns = "itemId")})
public class Orders {
    @PrimaryKey(autoGenerate = true)
    private int orderId;
    @ColumnInfo(index = true)
    private int customerId;
    @ColumnInfo(index = true)
    private int itemId;

    public Orders(int customerId, int itemId) {
        this.customerId = customerId;
        this.itemId = itemId;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
