package com.example.roomlab;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Assuming you have a RecyclerView in your cart layout
        RecyclerView cartRecyclerView = findViewById(R.id.cartRecyclerView);

        // Create an instance of CartViewModel
        CartViewModel cartViewModel = new CartViewModel(getApplication());

        // Get all items from the cart using CartViewModel
        List<CartItem> cartItems = cartViewModel.getAllCartItems().getValue();

        // Create an adapter for the RecyclerView
        CartAdapter cartAdapter = new CartAdapter(cartItems);

        // Set the layout manager and adapter for the RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);


    }


}
