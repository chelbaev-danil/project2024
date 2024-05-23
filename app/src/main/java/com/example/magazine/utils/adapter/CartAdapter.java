package com.example.magazine.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magazine.R;
import com.example.magazine.utils.model.FurnitureCart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private CartClickedListeners cartClickedListeners;
    private List<FurnitureCart> furnitureCartList;

    public CartAdapter(CartClickedListeners cartClickedListeners) {
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setFurnitureCartList(List<FurnitureCart> furnitureCartList){
        this.furnitureCartList = furnitureCartList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        FurnitureCart furnitureCart = furnitureCartList.get(position);

        holder.furnitureNameTv.setText(furnitureCart.getFurnitureName());
        holder.FurnitureQuantity.setText(furnitureCart.getQuantity() + "");
        holder.furniturePriceTv.setText(furnitureCart.getTotalItemPrice() + "");
        holder.furnitureImageView.setImageResource(furnitureCart.getFurnitureImage());

        holder.deleteFurnitureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(furnitureCart);
            }
        });
        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(furnitureCart);
            }
        });

        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(furnitureCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(furnitureCartList == null){
            return 0;
        }else{
            return furnitureCartList.size();
        }
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private TextView furnitureNameTv, furniturePriceTv, FurnitureQuantity;
        private ImageView deleteFurnitureBtn;
        private ImageView furnitureImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            furnitureNameTv = itemView.findViewById(R.id.eachCartItemName);
            furniturePriceTv = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteFurnitureBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            furnitureImageView = itemView.findViewById(R.id.eachCartItemIV);
            FurnitureQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);

        }
    }

    public interface CartClickedListeners {
        void onDeleteClicked(FurnitureCart furnitureCart);

        void onPlusClicked(FurnitureCart furnitureCart);

        void onMinusClicked(FurnitureCart furnitureCart);
    }
}
