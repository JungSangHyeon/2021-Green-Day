package com.example.greenday.view.trackList.listAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.ListAdapter;

import com.example.greenday.databinding.ItemTrackBinding;
import com.example.greenday.iTunes.Track;
import com.example.greenday.view.trackList.TrackViewHolder;
import com.example.greenday.viewmodel.TrackListViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends ListAdapter<Track, TrackViewHolder> implements Adapter {

    private final ObservableArrayList<Track> tracks;
    private final TrackListViewModel model;

    public FavoriteAdapter(TrackListViewModel model) {
        super(new TrackDiffUtil());
        this.model=model;
        this.tracks = model.getFavorites();
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTrackBinding binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new TrackViewHolder(binding, model);
    }
    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) { holder.setData(getItem(position)); }

    @Override
    public void datasetChanged() { submitList(tracks); }

    @Override
    public void submitList(final List<Track> list) { super.submitList(list != null ? new ArrayList<>(list) : null); }
}