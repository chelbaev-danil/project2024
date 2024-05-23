package com.example.magazine.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magazine.R;
import com.example.magazine.utils.model.FurnitureItem;

import java.util.List;

public class FurnitureItemAdapter extends RecyclerView.Adapter<FurnitureItemAdapter.FurnitureItemViewHolder> {
    Context mContext;
    private List<FurnitureItem> furnitureItemList;
    private FurnitureClickedListener furnitureClickedListener;
    public FurnitureItemAdapter(FurnitureClickedListener furnitureClickedListener){
        this.furnitureClickedListener = furnitureClickedListener;
    }
    public void setFurnitureItemList(List<FurnitureItem> furnitureItemList){
        this.furnitureItemList = furnitureItemList;
    }

    @NonNull
    @Override
    public FurnitureItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_furniture, parent, false);
        return new FurnitureItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureItemViewHolder holder, int position) {
        FurnitureItem furnitureItem = furnitureItemList.get(position);
        holder.furnitureNameTv.setText(furnitureItem.getFurnitureName());
        holder.furniturePriceTv.setText(String.valueOf(furnitureItem.getPrice()));
        holder.furnitureImageView.setImageResource(furnitureItem.getFurnitureImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                furnitureClickedListener.onCardClicked(furnitureItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                furnitureClickedListener.onAddToCartBtnClicked(furnitureItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(furnitureItemList == null){
            return 0;
        }else{
            return furnitureItemList.size();
        }

    }

    public class FurnitureItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView furnitureImageView , addToCartBtn;
        private TextView furnitureNameTv, furniturePriceTv;
        private CardView cardView;

        public FurnitureItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.eachFurnitureCardView);
            addToCartBtn = itemView.findViewById(R.id.eachFurnitureAddToCartBtn);
            furnitureNameTv = itemView.findViewById(R.id.eachFurnitureName);
            furnitureImageView = itemView.findViewById(R.id.eachFurnitureIv);
            furniturePriceTv = itemView.findViewById(R.id.eachFurniturePriceTv);

        }
    }

    public interface FurnitureClickedListener{
        void onCardClicked(FurnitureItem furnitureItem);
        void onAddToCartBtnClicked(FurnitureItem furnitureItem);
    }
}
