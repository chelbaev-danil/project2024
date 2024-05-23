package com.example.magazine.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.magazine.dao.CartDao;
import com.example.magazine.utils.model.FurnitureCart;

@Database(entities = {FurnitureCart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDao cartDao();
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , CartDatabase.class, "FurnitureDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}
