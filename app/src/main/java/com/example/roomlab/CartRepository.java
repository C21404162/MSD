package com.example.roomlab;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CartRepository {
    private CartDao cartDao;
    private LiveData<List<CartItem>> allCartItems;

    public CartRepository(Application application) {
        CartDatabase database = CartDatabase.getInstance(application);
        cartDao = database.cartDao();
        allCartItems = cartDao.getAllCartItems();
    }

    public void insert(CartItem cartItem) {
        new InsertCartItemAsyncTask(cartDao).execute(cartItem);
    }

    public void update(CartItem cartItem) {
        new UpdateCartItemAsyncTask(cartDao).execute(cartItem);
    }

    public void delete(CartItem cartItem) {
        new DeleteCartItemAsyncTask(cartDao).execute(cartItem);
    }

    public LiveData<List<CartItem>> getAllCartItems() {
        return allCartItems;
    }

    private static class InsertCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
        private CartDao cartDao;

        private InsertCartItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.insert(cartItems[0]);
            return null;
        }
    }

    private static class UpdateCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
        private CartDao cartDao;

        private UpdateCartItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.update(cartItems[0]);
            return null;
        }
    }

    private static class DeleteCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
        private CartDao cartDao;

        private DeleteCartItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.delete(cartItems[0]);
            return null;
        }
    }
}

