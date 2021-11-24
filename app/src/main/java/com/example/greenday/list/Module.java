package com.example.greenday.list;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greenday.network.Track;

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
    public static void changeFavorite(RecyclerView recyclerView, ObservableArrayList<Track> tracks) {
        ((TrackAdapter)recyclerView.getAdapter()).updateTrack();
    }
}
