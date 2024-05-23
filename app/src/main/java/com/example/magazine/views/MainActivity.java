package com.example.magazine.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magazine.R;
import com.example.magazine.utils.adapter.FurnitureItemAdapter;
import com.example.magazine.utils.model.FurnitureCart;
import com.example.magazine.utils.model.FurnitureItem;
import com.example.magazine.viewmodel.CartViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FurnitureItemAdapter.FurnitureClickedListener {

    private RecyclerView recyclerView;
    private List<FurnitureItem> furnitureItemList;
    private FurnitureItemAdapter adapter;
    private CartViewModel viewModel;
    private List<FurnitureCart> furnitureCartList;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();
        setUpList();

        viewModel.getAllCartItems().observe(this, new Observer<List<FurnitureCart>>() {
            @Override
            public void onChanged(List<FurnitureCart> furnitureCarts) {
                furnitureCartList.addAll(furnitureCarts);
            }
        });
        adapter.setFurnitureItemList(furnitureItemList);
        recyclerView.setAdapter(adapter);

        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCartItems().observe(this, new Observer<List<FurnitureCart>>() {
            @Override
            public void onChanged(List<FurnitureCart> shoeCarts) {
                furnitureCartList.addAll(shoeCarts);
            }
        });
    }

    private void setUpList(){
        furnitureItemList.add(new FurnitureItem("Кровать 3-х ярусная выдвижная с верхней крышкой",R.drawable.item1, 1500));
        furnitureItemList.add(new FurnitureItem("Кровать 3-х ярусная выдвижная без верхней крышки",R.drawable.item2, 3234));
        furnitureItemList.add(new FurnitureItem("Кровать детская односпальная с бортиком",R.drawable.item4, 4552));
        furnitureItemList.add(new FurnitureItem("Кровать детская односпальная, резная",R.drawable.item5, 3233));
        furnitureItemList.add(new FurnitureItem("Кровать детская односпальная",R.drawable.item3, 2221));



    }


    private void initializeVariables() {
        cartImageView = findViewById(R.id.cartIv);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        furnitureCartList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        furnitureItemList = new ArrayList<>();

        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new FurnitureItemAdapter(this);
    }

    @Override
    public void onCardClicked(FurnitureItem furnitureItem) {
        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra("furnitureItem", furnitureItem);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(FurnitureItem furnitureItem) {
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

        makeSnackBar("Item Added To Cart");
    }

    private void makeSnackBar(String msg){
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT)
                .setAction("Go To Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                    }
                }).show();
    }
}