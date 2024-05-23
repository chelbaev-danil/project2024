package com.example.magazine.utils.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "furniture_table")
public class FurnitureCart {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String FurnitureName;
    private int FurnitureImage;
    private double Price;

    private int quantity;
    private double totalItemPrice;

    public String getFurnitureName() {
        return FurnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        FurnitureName = furnitureName;
    }

    public int getFurnitureImage() {
        return FurnitureImage;
    }

    public void setFurnitureImage(int furnitureImage) {
        FurnitureImage = furnitureImage;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}
