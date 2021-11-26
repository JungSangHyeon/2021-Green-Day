package com.example.greenday.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.databinding.ObservableArrayList;

import com.example.greenday.database.Favorite;
import com.example.greenday.database.FavoriteDao;
import com.example.greenday.iTunes.ItunesApi;
import com.example.greenday.iTunes.Track;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class RepositoryImpl implements Repository{

    private ItunesApi api;
    private FavoriteDao dao;
    private final ObservableArrayList<Track> trackList, favorite;
    private ArrayList<Integer> beforeLoadTrackIds;

    public RepositoryImpl(FavoriteDao dao, ItunesApi api) {
        this.dao=dao;
        this.api=api;
        trackList = new ObservableArrayList<>();
        favorite = new ObservableArrayList<>();
        beforeLoadTrackIds = new ArrayList<>();
    }

    @Override
    public void loadTrackList(int offset) {
        api.search("greenday", "song", offset, 20)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        trackSearchResult -> {
                            ArrayList<Integer> trackIds = new ArrayList<>();
                            for (Track track : trackSearchResult.getResults()) {
                                if(!beforeLoadTrackIds.contains(track.getTrackId())){
                                    track.setFavorite(dao.getCount(track.getTrackId()) != 0);
                                    trackList.add(track);
                                    trackIds.add(track.getTrackId());
                                }
                            }
                            beforeLoadTrackIds = trackIds;
                        },
                        throwable -> Log.e("FAIL", throwable.getMessage())
                );
    }

    @Override
    public void loadFavorites() {
        dao.get()
                .subscribeOn(Schedulers.io())
                .subscribe(favorites -> getFavoriteAndLoad(favorites));
    }
    private void getFavoriteAndLoad(List<Favorite> favorites) {
        String ids = favorites.toString().replace("[", "").replace("]", "");
        api.searchWithTrackId(ids)
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

    @Override
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

    @Override public ObservableArrayList<Track> getTrackList() { return trackList; }
    @Override public ObservableArrayList<Track> getFavorite() { return favorite; }
}
