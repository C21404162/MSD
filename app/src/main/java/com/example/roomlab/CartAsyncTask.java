package com.example.roomlab;

import android.os.AsyncTask;

public class CartAsyncTask {

    public static class InsertCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
        private CartDao cartDao;

        public InsertCartItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.insert(cartItems[0]);
            return null;
        }
    }

    public static class UpdateCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
        private CartDao cartDao;

        public UpdateCartItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.update(cartItems[0]);
            return null;
        }
    }

    public static class DeleteCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
        private CartDao cartDao;

        public DeleteCartItemAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.delete(cartItems[0]);
            return null;
        }
    }
}
