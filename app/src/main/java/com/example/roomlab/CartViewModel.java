package com.example.roomlab;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private CartRepository repository;
    private LiveData<List<CartItem>> allCartItems;

    public CartViewModel(Application application) {
        super(application);
        repository = new CartRepository(application);
        allCartItems = repository.getAllCartItems();
    }

    // Non-static insert method
    public void insert(CartItem cartItem) {
        repository.insert(cartItem);
    }

    public void update(CartItem cartItem) {
        repository.update(cartItem);
    }

    public void delete(CartItem cartItem) {
        repository.delete(cartItem);
    }

    public LiveData<List<CartItem>> getAllCartItems() {
        return allCartItems;
    }
}


