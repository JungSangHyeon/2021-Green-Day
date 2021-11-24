package com.example.greenday.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.greenday.iTunes.Track;
import com.example.greenday.repository.Repository;

import lombok.Getter;

@Getter
public class TrackListViewModel extends ViewModel {

    private final Repository repository;
    private final ObservableArrayList<Track> trackList, favorites;

    public TrackListViewModel(){
        repository = new Repository();
        trackList = repository.getTrackList();
        favorites = repository.getFavorite();
        loadTrackList(0);
        loadFavorite();
    }

    public void loadTrackList(int offset) { repository.loadTrackList(offset); }
    public void loadFavorite() { repository.loadFavorites(); }
    public void favoriteChange(Track track, boolean checked) { repository.favoriteChange(track, checked); }
}
