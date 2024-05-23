package com.example.magazine.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.magazine.utils.model.FurnitureCart;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    void insertCartItem(FurnitureCart furnitureCart);
    @Query("SELECT * FROM furniture_table")
    LiveData<List<FurnitureCart>> getAllCartItems();

    @Delete
    void deleteCartItem(FurnitureCart furnitureCart);

    @Query("UPDATE furniture_table SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id, int quantity);

    @Query("UPDATE furniture_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id , double totalItemPrice);

    @Query("DELETE FROM furniture_table")
    void deleteAllItems();
}
