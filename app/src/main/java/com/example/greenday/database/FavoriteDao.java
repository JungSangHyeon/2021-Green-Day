package com.example.greenday.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    List<Favorite> get();

    @Query("SELECT COUNT(*) FROM favorite WHERE track_id = :id")
    int getCount(int id);

    @Insert
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);
}
