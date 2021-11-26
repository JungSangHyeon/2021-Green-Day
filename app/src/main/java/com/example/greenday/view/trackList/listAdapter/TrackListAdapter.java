package com.example.greenday.view.trackList.listAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.ListAdapter;

import com.example.greenday.databinding.ItemTrackBinding;
import com.example.greenday.remoteDataSource.Track;
import com.example.greenday.view.trackList.TrackViewHolder;
import com.example.greenday.viewmodel.TrackListViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrackListAdapter extends ListAdapter<Track, TrackViewHolder> {

    private static final int LOADING_MARGIN = 10;

    private final TrackListViewModel model;

    public TrackListAdapter(TrackListViewModel model) {
        super(new TrackDiffUtil());
        this.model=model;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTrackBinding binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new TrackViewHolder(binding, model);
    }
    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        holder.setData(getItem(position));
        if (position == getItemCount() - LOADING_MARGIN) model.loadTrackList(getItemCount());
    }

    @Override
    public void submitList(final List<Track> list) { super.submitList(list != null ? new ArrayList<>(list) : null); }
}