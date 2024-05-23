package com.example.magazine.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FurnitureItem implements Parcelable {

    private String FurnitureName;
    private  int FurnitureImage;
    private double Price;

    public FurnitureItem(String furnitureName, int furnitureImage, double price) {
        FurnitureName = furnitureName;
        FurnitureImage = furnitureImage;
        Price = price;
    }

    protected FurnitureItem(Parcel in) {
        FurnitureName = in.readString();
        FurnitureImage = in.readInt();
        Price = in.readDouble();
    }

    public static final Creator<FurnitureItem> CREATOR = new Creator<FurnitureItem>() {
        @Override
        public FurnitureItem createFromParcel(Parcel in) {
            return new FurnitureItem(in);
        }

        @Override
        public FurnitureItem[] newArray(int size) {
            return new FurnitureItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeString(FurnitureName);
        parcel.writeInt(FurnitureImage);
        parcel.writeDouble(Price);
    }


}
