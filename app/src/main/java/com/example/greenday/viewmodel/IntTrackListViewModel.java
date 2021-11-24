package com.example.greenday.viewmodel;

import androidx.databinding.ObservableArrayList;

import com.example.greenday.iTunes.Track;

public interface IntTrackListViewModel {
    ObservableArrayList<Track> getList();
    void load(int offset, int limit);
}
