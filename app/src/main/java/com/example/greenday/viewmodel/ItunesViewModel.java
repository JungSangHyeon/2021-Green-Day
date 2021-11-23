package com.example.greenday.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.greenday.network.API;
import com.example.greenday.network.Network;
import com.example.greenday.network.Track;
import com.example.greenday.network.TrackSearchResult;

import java.util.ArrayList;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

@Getter
public class ItunesViewModel extends ViewModel {

    Retrofit retrofit = Network.getInstance();
    API api = retrofit.create(API.class);
    ArrayList<Integer> trackIds = new ArrayList<>();
    int offset = 0;
    boolean ready = true;
    ObservableArrayList<Track> observableArrayList = new ObservableArrayList<>();

    public void get(){
        ready=false;
        new Thread(){
            @Override
            public void run() {
                api.search("greenday", "song", offset, 20).enqueue(new Callback<TrackSearchResult>() {
                    @Override
                    public void onResponse(Call<TrackSearchResult> call, retrofit2.Response<TrackSearchResult> response) {
                        TrackSearchResult r = response.body();
                        for(Track track : r.getResults()){
                            if(!trackIds.contains(track.getTrackId())){
                                trackIds.add(track.getTrackId());
                                observableArrayList.add(track);
                            }
                        }
                        ready = true;
                    }
                    @Override
                    public void onFailure(Call<TrackSearchResult> call, Throwable t) {
                        Log.e("FAIL", t.getMessage());
                        ready = true;
                    }
                });
                offset+=20;
            }
        }.start();
    }


    public boolean ready() {
        return ready;
    }
}
