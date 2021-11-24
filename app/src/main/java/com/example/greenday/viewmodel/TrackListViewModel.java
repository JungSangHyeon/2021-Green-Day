package com.example.greenday.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.greenday.iTunes.Track;
import com.example.greenday.repository.RepositoryImpl;

import lombok.Getter;

@Getter
public class TrackListViewModel extends ViewModel {

    private final RepositoryImpl repository;
    private final ObservableArrayList<Track> trackList, favorites;

    public TrackListViewModel(){
        repository = new RepositoryImpl();
        trackList = repository.getTrackList();
        favorites = repository.getFavorite();
    }

    public void loadTrackList(int offset, int limit) { repository.loadTrackList(offset, limit); }
    public void loadFavorite() { repository.loadFavorites(); }
    public void changeFavorite(Track track) { repository.changeFavorite(track); }
}
