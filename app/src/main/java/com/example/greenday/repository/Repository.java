package com.example.greenday.repository;

public interface Repository {
    void loadTrackList(int offset, int limit);
    void loadFavorites();
}
