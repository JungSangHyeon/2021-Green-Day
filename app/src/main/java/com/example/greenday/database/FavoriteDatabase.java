package com.example.greenday.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static volatile FavoriteDatabase INSTANCE;

    public static void init(final Context context) {
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FavoriteDatabase.class, "favorite")
                .fallbackToDestructiveMigration() // 버전 바꾸면 리셋
                .build();
    }

    public static FavoriteDatabase getDB(){return INSTANCE;}
    public abstract FavoriteDao favoriteDao();
}
