package com.example.greenday.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    Single<List<Favorite>> get();

    @Query("SELECT COUNT(*) FROM favorite WHERE track_id = :id")
    int getCount(int id);

    @Insert
    Completable insert(Favorite favorite);

    @Delete
    Completable delete(Favorite favorite);
}
