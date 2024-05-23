package com.example.magazine.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.magazine.R;
import com.example.magazine.utils.model.FurnitureCart;
import com.example.magazine.utils.model.FurnitureItem;
import com.example.magazine.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    private ImageView furnitureImageView;
    private TextView furnitureNameTv, furniturePriceTv;
    private AppCompatButton addToCartBtn;
    private FurnitureItem furnitureItem;
    private CartViewModel viewModel;
    private List<FurnitureCart> furnitureCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        furnitureItem = getIntent().getParcelableExtra("furnitureItem");
        initializeVariables();
        viewModel.getAllCartItems().observe(this, new Observer<List<FurnitureCart>>() {
            @Override
            public void onChanged(List<FurnitureCart> furnitureCarts) {
                furnitureCartList.addAll(furnitureCarts);
            }
        });
        if(furnitureItem != null){
            setDataToWidgets();
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToRoom();
            }
        });
    }

    private void insertToRoom(){
        FurnitureCart furnitureCart = new FurnitureCart();
        furnitureCart.setFurnitureName(furnitureItem.getFurnitureName());
        furnitureCart.setPrice(furnitureItem.getPrice());
        furnitureCart.setFurnitureImage(furnitureItem.getFurnitureImage());

        final int[] quantity = {1};
        final int[] id = new int[1];
        if(!furnitureCartList.isEmpty()){
            for (int i = 0; i < furnitureCartList.size(); i++) {
                if (furnitureCart.getFurnitureName().equals(furnitureCartList.get(i).getFurnitureName())) {
                    quantity[0] = furnitureCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = furnitureCartList.get(i).getId();
                }
            }
        }

        if(quantity[0] == 1){
            furnitureCart.setQuantity(quantity[0]);
            furnitureCart.setTotalItemPrice(quantity[0] * furnitureCart.getPrice());
            viewModel.insertCartItem(furnitureCart);
        }else{
            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0]*furnitureCart.getPrice());
        }

        startActivity(new Intent(DetailedActivity.this, CartActivity.class));
    }
    private void setDataToWidgets(){
        furnitureNameTv.setText(furnitureItem.getFurnitureName());
        furniturePriceTv.setText(String.valueOf(furnitureItem.getPrice()));
        furnitureImageView.setImageResource(furnitureItem.getFurnitureImage());


    }
     private void initializeVariables(){

        furnitureCartList = new ArrayList<>();
        furnitureImageView = findViewById(R.id.detailActivityFurnitureIV);
        furnitureNameTv = findViewById(R.id.detailActivityFurnitureNameTv);
        furniturePriceTv = findViewById(R.id.detailActivityFurniturePriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
     }
}