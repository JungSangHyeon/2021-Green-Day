package com.example.greenday.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.greenday.database.FavoriteDao;
import com.example.greenday.iTunes.ItunesApi;
import com.example.greenday.iTunes.Track;
import com.example.greenday.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TrackListViewModel extends ViewModel {

    private final Repository repository;

    @Inject
    public TrackListViewModel(FavoriteDao dao, ItunesApi itunesApi){
        repository = new Repository(dao, itunesApi);
        trackList = repository.getTrackList();
        favorites = repository.getFavorite();
        loadTrackList(0);
        loadFavorite();
    }

    private final ObservableArrayList<Track> trackList, favorites;

    public void loadTrackList(int offset) { repository.loadTrackList(offset); }
    public void loadFavorite() { repository.loadFavorites(); }
    public void favoriteChange(Track track, boolean checked) { repository.favoriteChange(track, checked); }

    public ObservableArrayList<Track> getTrackList() { return trackList; }
    public ObservableArrayList<Track> getFavorites() { return favorites; }
}
