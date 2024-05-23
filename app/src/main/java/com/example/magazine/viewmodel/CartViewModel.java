package com.example.magazine.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.magazine.repository.CartRepo;
import com.example.magazine.utils.model.FurnitureCart;

import java.util.List;

public class CartViewModel extends AndroidViewModel{
    private CartRepo cartRepo;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);

    }
    public LiveData<List<FurnitureCart>> getAllCartItems() {
        return cartRepo.getAllCartItemsLiveData();
    }
    public void insertCartItem(FurnitureCart furnitureCart){
        cartRepo.insertCartItem(furnitureCart);
    }

    public void updateQuantity(int id, int quantity){
        cartRepo.updateQuantity(id, quantity);
    }

    public void updatePrice(int id, double price) {
        cartRepo.updatePrice(id, price);
    }

    public void deleteCartItem(FurnitureCart furnitureCart) {
        cartRepo.deleteCartItem(furnitureCart);
    }

    public void deleteAllCartItems() {
        cartRepo.deleteAllCartItems();
    }

}
