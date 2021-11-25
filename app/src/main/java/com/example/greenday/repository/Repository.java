package com.example.greenday.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.databinding.ObservableArrayList;

import com.example.greenday.database.Favorite;
import com.example.greenday.database.FavoriteDao;
import com.example.greenday.database.FavoriteDatabase;
import com.example.greenday.iTunes.API;
import com.example.greenday.iTunes.Network;
import com.example.greenday.iTunes.Track;

import java.util.List;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

//@Getter
@SuppressLint("CheckResult")
public class Repository {

    private final API api;
    private final FavoriteDao dao;
    private final ObservableArrayList<Track> trackList, favorite;

    public Repository() {
        api = Network.getInstance().create(API.class);
        dao = FavoriteDatabase.getDB().favoriteDao(); // 일단 메인에서 만들어서 스태택주입
        trackList = new ObservableArrayList<>();
        favorite = new ObservableArrayList<>();
    }


    public ObservableArrayList<Track> getTrackList() {
        return trackList;
    }

    public ObservableArrayList<Track> getFavorite() {
        return favorite;
    }

    public void loadTrackList(int offset) {
        api.search("greenday", "song", offset, 20)
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
}
