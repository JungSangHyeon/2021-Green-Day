package com.example.greenday.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.greenday.network.API;
import com.example.greenday.network.Network;
import com.example.greenday.network.Track;
import com.example.greenday.network.TrackSearchResult;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;

@Getter
public class ItunesViewModel extends ViewModel {

    private final API api = Network.getInstance().create(API.class);
    private final ObservableArrayList<Track> tracks = new ObservableArrayList<>();
    private int offset = 0;
    private boolean ready = true;

    public void get() {
        ready = false;
        new Thread() {
            @Override
            public void run() {
                api.search("greenday", "song", offset, 20)
                        .enqueue(new Callback<TrackSearchResult>() {
                            @Override
                            public void onResponse(Call<TrackSearchResult> call, retrofit2.Response<TrackSearchResult> response) {
                                tracks.addAll(response.body().getResults());
                                ready = true;
                            }
                            @Override
                            public void onFailure(Call<TrackSearchResult> call, Throwable t) {
                                Log.e("FAIL", t.getMessage());
                                ready = true;
                            }
                        });
                offset += 20;
            }
        }.start();
    }

    public boolean ready() {
        return ready;
    }
}
