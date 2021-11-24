package com.example.greenday.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.greenday.iTunes.Track;
import com.example.greenday.repository.RepositoryImpl;

import lombok.Getter;

@Getter
public class FavoriteViewModel extends ViewModel implements IntTrackListViewModel {

    private final RepositoryImpl repository;
    private final ObservableArrayList<Track> favorites;

    public FavoriteViewModel(){
        repository = new RepositoryImpl();
        favorites = repository.getFavorite();
        load(0, 20);
    }

    @Override
    public void load(int offset, int limit) { repository.loadFavorites(offset, limit); }

    @Override
    public ObservableArrayList<Track> getList() { return favorites; }
}
