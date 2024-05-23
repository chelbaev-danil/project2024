package com.example.magazine.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.magazine.dao.CartDao;
import com.example.magazine.database.CartDatabase;
import com.example.magazine.utils.model.FurnitureCart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {

    private CartDao cartDao;
    private LiveData<List<FurnitureCart>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<FurnitureCart>> getAllCartItemsLiveData() {
        return allCartItemsLiveData;
    }

    public CartRepo(Application application){
        cartDao = CartDatabase.getInstance(application).cartDao();
        allCartItemsLiveData = cartDao.getAllCartItems();
    }

    public void insertCartItem(FurnitureCart furniture){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.insertCartItem(furniture);
            }
        });
    }

    public void deleteCartItem(FurnitureCart furniture){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.deleteCartItem(furniture);
            }
        });
    }

    public void updateQuantity(int id, int quantity){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.updateQuantity(id, quantity);
            }
        });
    }

    public void updatePrice(int id, double price){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.updatePrice(id, price);
            }
        });
    }

    public void deleteAllCartItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.deleteAllItems();
            }
        });
    }
}
