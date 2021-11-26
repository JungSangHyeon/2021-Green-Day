package com.example.greenday.localDataSource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Favorite.class}, version = 1, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {
    public abstract FavoriteDao favoriteDao();

    public static FavoriteDao getFavoriteDao(Context context){
        return Room.databaseBuilder(context, FavoriteDatabase.class, "favorite")
                .fallbackToDestructiveMigration().build().favoriteDao();
    }
}
