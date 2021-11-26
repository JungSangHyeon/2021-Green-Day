package com.example.greenday.view.trackList.listAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.greenday.iTunes.Track;

public class TrackDiffUtil extends DiffUtil.ItemCallback<Track>{

    @Override
    public boolean areItemsTheSame(@NonNull Track oldItem, @NonNull Track newItem) {
        return oldItem.getTrackId() == newItem.getTrackId();
    }
    @Override
    public boolean areContentsTheSame(@NonNull Track oldItem, @NonNull Track newItem) {
        return oldItem.getTrackName().equals(newItem.getTrackName()) &&
                oldItem.getArtistName().equals(newItem.getArtistName()) &&
                oldItem.getArtworkUrl100().equals(newItem.getArtworkUrl100()) &&
                oldItem.getCollectionName().equals(newItem.getCollectionName()) &&
                oldItem.isFavorite()==newItem.isFavorite();
    }
}

