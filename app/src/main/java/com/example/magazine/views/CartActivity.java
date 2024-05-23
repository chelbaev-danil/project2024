package com.example.magazine.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magazine.R;
import com.example.magazine.utils.adapter.CartAdapter;
import com.example.magazine.utils.model.FurnitureCart;
import com.example.magazine.viewmodel.CartViewModel;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView totalCartPriceTv, textView;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<FurnitureCart>>() {
            @Override
            public void onChanged(List<FurnitureCart> furnitureCarts) {
                double price = 0;
                cartAdapter.setFurnitureCartList(furnitureCarts);
                for (int i = 0; i < furnitureCarts.size(); i++) {
                    price = price + furnitureCarts.get(i).getTotalItemPrice();
                }

                totalCartPriceTv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.deleteAllCartItems();
                textView.setVisibility(View.INVISIBLE);
                checkoutBtn.setVisibility(View.INVISIBLE);
                totalCartPriceTv.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
    }

    private void initializeVariables() {

        cartAdapter = new CartAdapter(this);
        textView = findViewById(R.id.textView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);

    }

    @Override
    public void onDeleteClicked(FurnitureCart furnitureCart) {
        cartViewModel.deleteCartItem(furnitureCart);
    }

    @Override
    public void onPlusClicked(FurnitureCart furnitureCart) {
        int quantity = furnitureCart.getQuantity() + 1;
        cartViewModel.updateQuantity(furnitureCart.getId() , quantity);
        cartViewModel.updatePrice(furnitureCart.getId() , quantity*furnitureCart.getPrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(FurnitureCart furnitureCart) {
        int quantity = furnitureCart.getQuantity() - 1;
        if (quantity != 0){
            cartViewModel.updateQuantity(furnitureCart.getId() , quantity);
            cartViewModel.updatePrice(furnitureCart.getId() , quantity*furnitureCart.getPrice());
            cartAdapter.notifyDataSetChanged();
        }else{
            cartViewModel.deleteCartItem(furnitureCart);
        }

    }
}