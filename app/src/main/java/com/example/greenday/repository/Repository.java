package com.example.greenday.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.databinding.ObservableArrayList;

import com.example.greenday.database.Favorite;
import com.example.greenday.database.FavoriteDao;
import com.example.greenday.iTunes.ItunesApi;
import com.example.greenday.iTunes.Track;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class Repository {

    private final ItunesApi itunesApi;
    private final FavoriteDao dao;
    private final ObservableArrayList<Track> trackList, favorite;

    public Repository(FavoriteDao dao, ItunesApi itunesApi) {
        this.itunesApi = itunesApi;
        this.dao=dao;
        trackList = new ObservableArrayList<>();
        favorite = new ObservableArrayList<>();
    }

    public void loadTrackList(int offset) {
        itunesApi.search("greenday", "song", offset, 20)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        trackSearchResult -> {
                            for (Track track : trackSearchResult.getResults()) {
                                track.setFavorite(dao.getCount(track.getTrackId()) != 0);
                                trackList.add(track);
                            }
                        },
                        throwable -> Log.e("FAIL", throwable.getMessage())
                );
    }

    public void loadFavorites() {
        dao.get()
                .subscribeOn(Schedulers.io())
                .subscribe(favorites -> getFavoriteAndLoad(favorites));
    }
    private void getFavoriteAndLoad(List<Favorite> favorites) {
        String ids = favorites.toString().replace("[", "").replace("]", "");
        itunesApi.searchWithTrackId(ids)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        trackSearchResult -> {
                            for (Track track : trackSearchResult.getResults()) {
                                track.setFavorite(true);
                                favorite.add(track);
                            }
                        },
                        throwable -> Log.e("FAIL", throwable.getMessage())
                );
    }

    public void favoriteChange(Track track, boolean checked){
        Track trackInTrackList = find(trackList, track.getTrackId());
        if(trackInTrackList!=null) trackInTrackList.setFavorite(checked);

        if(checked){
            favorite.add(track);
        }else{
            Track trackInFavorite = find(favorite, track.getTrackId());
            if(trackInFavorite!=null) favorite.remove(trackInFavorite);
        }

        Favorite favorite = new Favorite();
        favorite.trackId=track.getTrackId();
        if(checked) dao.insert(favorite).subscribeOn(Schedulers.io()).subscribe();
        else dao.delete(favorite).subscribeOn(Schedulers.io()).subscribe();
    }
    public Track find(ObservableArrayList<Track> list, int id){
        for(Track track : list){
            if(track.getTrackId()==id) return track;
        }
        return null;
    }

    public ObservableArrayList<Track> getTrackList() {
        return trackList;
    }
    public ObservableArrayList<Track> getFavorite() {
        return favorite;
    }
}
