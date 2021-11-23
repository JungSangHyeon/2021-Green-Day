package com.example.greenday;

import android.os.Bundle;

import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greenday.databinding.FragmentTrackListBinding;
import com.example.greenday.databinding.ItemTrackBinding;
import com.example.greenday.list.TrackAdapter;
import com.example.greenday.network.API;
import com.example.greenday.network.Network;
import com.example.greenday.network.Response;
import com.example.greenday.network.Track;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class TrackListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ObservableArrayList<Track> observableArrayList = new ObservableArrayList<>();

        FragmentTrackListBinding binding = FragmentTrackListBinding.inflate(inflater, container, false);
        binding.setTrackList(observableArrayList);
        binding.title.setText("TrackList");
        RecyclerView rv = binding.trackList;
        rv.setAdapter(new TrackAdapter(observableArrayList));

        Retrofit retrofit = Network.getInstance();
        API api = retrofit.create(API.class);
        new Thread(){
            @Override
            public void run() {
                api.search("greenday", "song", 0, 3).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Response r = response.body();
                        observableArrayList.addAll(r.getResults());
                    }
                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.e("FAIL", t.getMessage());
                    }
                });

                api.search("greenday", "song", 3, 6).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Response r = response.body();
                        observableArrayList.addAll(r.getResults());
                    }
                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.e("FAIL", t.getMessage());
                    }
                });

                api.search("greenday", "song", 6, 9).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Response r = response.body();
                        observableArrayList.addAll(r.getResults());
                    }
                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.e("FAIL", t.getMessage());
                    }
                });
            }
        }.start();
        return binding.getRoot();
    }

}