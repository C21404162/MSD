package com.example.roomlab;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CartItem.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase instance;

    public abstract CartDao cartDao();

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CartDatabase.class, "cart_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
