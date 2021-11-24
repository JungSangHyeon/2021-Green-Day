package com.example.greenday.repository;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.example.greenday.database.Favorite;
import com.example.greenday.database.FavoriteDao;
import com.example.greenday.database.FavoriteDatabase;
import com.example.greenday.iTunes.API;
import com.example.greenday.iTunes.Network;
import com.example.greenday.iTunes.Track;
import com.example.greenday.iTunes.TrackSearchResult;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;

@Getter
public class Repository { // 일단 메인에서 만들어서 스태택주입

    private final API api;
    private final FavoriteDao dao;
    private final ObservableArrayList<Track> trackList, favorite; // 둘이 연동 어캐 하누

    public Repository() {
        api = Network.getInstance().create(API.class);
        dao = FavoriteDatabase.getDB().favoriteDao();
        trackList = new ObservableArrayList<>();
        favorite = new ObservableArrayList<>();
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

        Favorite f = new Favorite();
        f.trackId=track.getTrackId();
        if(checked) FavoriteDatabase.SERVICE.execute(()->FavoriteDatabase.getDB().favoriteDao().insert(f));
        else FavoriteDatabase.SERVICE.execute(()->FavoriteDatabase.getDB().favoriteDao().delete(f));
    }

    public void loadTrackList(int offset) {
        new Thread() {
            @Override
            public void run() {
                api.search("greenday", "song", offset, 20)
                        .enqueue(new Callback<TrackSearchResult>() {
                            @Override
                            public void onResponse(Call<TrackSearchResult> call, retrofit2.Response<TrackSearchResult> response) {
                                for (Track track : response.body().getResults()) {
                                    FavoriteDatabase.SERVICE.execute(() -> {
                                        track.setFavorite(dao.getCount(track.getTrackId()) != 0);
                                        trackList.add(track);
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<TrackSearchResult> call, Throwable t) {
                                Log.e("FAIL", t.getMessage());
                            }
                        });
            }
        }.start();
    }

    public void loadFavorites() {
        new Thread() {
            @Override
            public void run() {
                FavoriteDatabase.SERVICE.execute(() -> {
                    String ids = dao.get().toString().replace("[", "").replace("]", "");
                    api.searchWithTrackId(ids)
                            .enqueue(new Callback<TrackSearchResult>() {
                                @Override
                                public void onResponse(Call<TrackSearchResult> call, retrofit2.Response<TrackSearchResult> response) {
                                    for (Track track : response.body().getResults()) {
                                        track.setFavorite(true);
                                        favorite.add(track);
                                    }
                                }

                                @Override
                                public void onFailure(Call<TrackSearchResult> call, Throwable t) {
                                    Log.e("FAIL", t.getMessage());
                                }
                            });
                });

            }
        }.start();
    }

    public Track find(ObservableArrayList<Track> list, int id){
        for(Track track : list){
            if(track.getTrackId()==id) return track;
        }
        return null;
    }
}
