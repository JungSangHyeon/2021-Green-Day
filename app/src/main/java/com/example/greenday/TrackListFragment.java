package com.example.greenday;

import android.os.Bundle;

import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greenday.databinding.FragmentTrackListBinding;
import com.example.greenday.list.TrackAdapter;
import com.example.greenday.network.API;
import com.example.greenday.network.Network;
import com.example.greenday.network.TrackSearchResult;
import com.example.greenday.network.Track;
import com.example.greenday.viewmodel.ItunesViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class TrackListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ItunesViewModel model = new ViewModelProvider(this).get(ItunesViewModel.class);

        FragmentTrackListBinding binding = FragmentTrackListBinding.inflate(inflater, container, false);
        binding.setTrackList(model.getObservableArrayList());
        binding.title.setText("TrackList");

        RecyclerView rv = binding.trackList;
        rv.setAdapter(new TrackAdapter(model.getObservableArrayList()));

        model.get();

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
                if (lastVisibleItemPosition == itemTotalCount-10 && model.ready()) {
                    model.get();
                }
            }
        });

        return binding.getRoot();
    }

}