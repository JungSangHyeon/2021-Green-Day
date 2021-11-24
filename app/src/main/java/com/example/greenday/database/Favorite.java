package com.example.greenday.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Favorite {

    @PrimaryKey
    @ColumnInfo(name = "track_id")
    public int trackId;

    @NotNull
    @Override
    public String toString() { return Integer.toString(trackId); }
}