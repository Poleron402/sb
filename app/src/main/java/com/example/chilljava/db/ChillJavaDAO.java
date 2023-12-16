package com.example.chilljava.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChillJavaDAO {
    @Insert
    void insert(User... users);
    @Update
    void update(User... users);
    @Delete
    void delete(User user);
    @Query("select * from "+ChillJavaDB.USER_TABLE)
    List<User> getAllUser();
    @Query("select * from "+ChillJavaDB.USER_TABLE+" where mUsername = :username")
    User getUserByUsername(String username);
    @Query("select * from "+ChillJavaDB.USER_TABLE+" where mUserId = :userId")
    User getUserById(int userId);


    @Insert
    void insert(Menu... items);
    @Update
    void update(Menu... items);
    @Delete
    void delete(Menu item);
    @Query("select * from "+ChillJavaDB.MENU_TABLE)
    List<Menu> getAllItems();
    @Query("select * from "+ChillJavaDB.MENU_TABLE+" where itemName like :itemName")
    List <Menu> getItemByName(String itemName);

    @Query("select * from "+ChillJavaDB.MENU_TABLE+" where itemName = :itemName")
    Menu getAnItemByName(String itemName);
    @Query("select * from "+ChillJavaDB.MENU_TABLE+" where itemId = :itemId")
    Menu getItemById(int itemId);

    @Insert
    void insert(Orders... orders);
    @Update
    void update(Orders... orders);
    @Delete
    void delete(Orders order);
    @Query("select * from "+ChillJavaDB.ORDERS_TABLE)
    List<Orders> getAllOrders();
    @Query("select * from "+ChillJavaDB.ORDERS_TABLE+" where orderId = :orderId")
    Orders getOrderById(int orderId);
    @Query("select * from "+ChillJavaDB.ORDERS_TABLE+" where customerId = :custId")
    List <Orders> getOrderByCustId(int custId);
}
