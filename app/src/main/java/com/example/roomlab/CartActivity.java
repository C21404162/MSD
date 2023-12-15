package com.example.roomlab;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView cartRecyclerView = findViewById(R.id.cartRecyclerView);

        // Create an instance of CartViewModel
        CartViewModel cartViewModel = new CartViewModel(getApplication());

        CartAdapter cartAdapter = new CartAdapter(new ArrayList<>());

        cartViewModel.getAllCartItems().observe(this, cartItems -> {
            // Update the UI or adapter when the data changes
            cartAdapter.setCartItems(cartItems);
        });

        // Set the layout manager and adapter for the RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);
    }
}

