package com.example.roomlab;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cartitem")
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String itemName;
    private String itemDescription;
    private String itemPrice;

    public CartItem(String itemName, String itemDescription, String itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public CartItem(String tmpItemName, double tmpItemPrice) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for itemName
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Getter and Setter for itemDescription
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    // Getter and Setter for itemPrice
    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
