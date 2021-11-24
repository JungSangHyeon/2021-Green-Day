package com.example.greenday.view.trackList;

import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greenday.database.Favorite;
import com.example.greenday.database.FavoriteDatabase;
import com.example.greenday.iTunes.Track;

public class Module {

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView).load(url).centerCrop().into(imageView);
    }

    @BindingAdapter("loadData")
    public static void loadData(RecyclerView recyclerView, ObservableArrayList<Track> tracks) {
        ((TrackAdapter)recyclerView.getAdapter()).updateTrack();
    }

    @BindingAdapter("changeFavorite")
    public static void changeFavorite(ToggleButton toggleButton, Track track) {
        toggleButton.setOnCheckedChangeListener((compoundButton, b) -> {
            if(track.isFavorite()!=toggleButton.isChecked()){
                track.setFavorite(toggleButton.isChecked());
                Favorite favorite = new Favorite();
                favorite.trackId=track.getTrackId();
                if(track.isFavorite()) FavoriteDatabase.SERVICE.execute(()->FavoriteDatabase.getDB().favoriteDao().insert(favorite));
                else FavoriteDatabase.SERVICE.execute(()->FavoriteDatabase.getDB().favoriteDao().delete(favorite));
            }
        });
    }
}
