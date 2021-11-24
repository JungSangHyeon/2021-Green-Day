package com.example.greenday.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.greenday.iTunes.Track;
import com.example.greenday.repository.RepositoryImpl;

public class TrackListViewModel extends ViewModel implements IntTrackListViewModel {

    private final RepositoryImpl repository;
    private final ObservableArrayList<Track> trackList;

    public TrackListViewModel(){
        repository = new RepositoryImpl();
        trackList = repository.getTrackList();
        load(0, 20);
    }

    @Override
    public void load(int offset, int limit) { repository.loadTrackList(offset, limit); }

    @Override
    public ObservableArrayList<Track> getList() { return trackList; }
}
