package com.example.greenday.view.trackList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenday.databinding.ItemTrackBinding;
import com.example.greenday.iTunes.Track;
import com.example.greenday.viewmodel.TrackListViewModel;

import org.jetbrains.annotations.NotNull;

public class TrackListAdapter extends RecyclerView.Adapter<TrackViewHolder> implements Adapter {

    private static final int LOADING_MARGIN = 10;

    private final ObservableArrayList<Track> tracks;
    private final TrackListViewModel model;
    private int sizeBeforeUpdate;

    public TrackListAdapter(TrackListViewModel model) {
        this.model=model;
        this.tracks = model.getTrackList();
    }

    @NotNull
    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTrackBinding binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new TrackViewHolder(binding, model);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrackViewHolder holder, int position) {
        holder.setData(tracks.get(position));
        if (position == getItemCount() - LOADING_MARGIN) model.loadTrackList(getItemCount());
    }

    @Override
    public void datasetChanged() {
        this.notifyItemRangeInserted(sizeBeforeUpdate, tracks.size() - sizeBeforeUpdate);
        sizeBeforeUpdate = tracks.size();
    }

    @Override
    public int getItemCount() { return tracks.size(); }
}