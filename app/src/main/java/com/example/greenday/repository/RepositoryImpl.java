package com.example.greenday.repository;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

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
public class RepositoryImpl implements Repository { // 일단 메인에서 만들어서 스태택주입

    private final API api;
    private final FavoriteDao dao;
    private final ObservableArrayList<Track> trackList, favorite; // 둘이 연동 어캐 하누

    public RepositoryImpl() {
        api = Network.getInstance().create(API.class);
        dao = FavoriteDatabase.getDB().favoriteDao();
        trackList = new ObservableArrayList<>();
        favorite = new ObservableArrayList<>();
    }

    public void changeFavorite(Track track){

    }

    @Override
    public void loadTrackList(int offset, int limit) {
        new Thread() {
            @Override
            public void run() {
                api.search("greenday", "song", offset, limit)
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

    @Override
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
}
