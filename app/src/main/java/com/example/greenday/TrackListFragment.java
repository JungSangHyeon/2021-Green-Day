package com.example.greenday;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greenday.network.API;
import com.example.greenday.network.Network;
import com.example.greenday.network.Response;
import com.example.greenday.network.Results;

import lombok.val;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class TrackListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_track_list, container, false);

        TextView tv = view.findViewById(R.id.title);
        tv.setText("Track List");

        Retrofit retrofit = Network.getInstance();
        API api = retrofit.create(API.class);
        new Thread(){
            @Override
            public void run() {
                api.search("greenday", "song", 0, 100).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Response r = response.body();
                        Log.e("SONG SIZE", ""+r.getResultCount());
                        for(Results results : r.getResults()){
                            Log.e("SONG", results.getTrackName());
                        }
                        RecyclerView rv = view.findViewById(R.id.trackList);
                        rv.setAdapter(new TrackAdapter(r));
                    }
                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.e("FAIL", t.getMessage());
                    }
                });

            }
        }.start();



        return view;
    }

}