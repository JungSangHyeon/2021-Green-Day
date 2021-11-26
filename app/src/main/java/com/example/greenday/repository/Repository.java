package com.example.greenday.repository;

import androidx.databinding.ObservableArrayList;

import com.example.greenday.iTunes.Track;

public interface Repository {

    void loadTrackList(int offset);
    void loadFavorites();
    void favoriteChange(Track track, boolean checked);

    ObservableArrayList<Track> getTrackList();
    ObservableArrayList<Track> getFavorite();
}
