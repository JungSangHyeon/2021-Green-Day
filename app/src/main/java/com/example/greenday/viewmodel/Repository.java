package com.example.greenday.viewmodel;

public interface Repository {
    void loadTrackList(int offset, int limit);
    void loadFavorites(int offset, int limit);
}
