package com.example.greenday.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.greenday.network.Track;

import lombok.Getter;

@Getter
public class ItunesViewModel extends ViewModel {

    private final RepositoryImpl repository;
    private final ObservableArrayList<Track> trackList, favorite;

    public ItunesViewModel(){
        repository = new RepositoryImpl();
        trackList = repository.getTrackList();
        favorite = repository.getFavorite();
    }

    public void loadTrackList(int offset, int limit) { repository.loadTrackList(offset, limit); }
    public void loadFavorites(int offset, int limit) { repository.loadFavorites(offset, limit); }
}
