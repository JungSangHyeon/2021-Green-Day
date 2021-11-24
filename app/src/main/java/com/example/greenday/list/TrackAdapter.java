package com.example.greenday.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenday.databinding.ItemTrackBinding;
import com.example.greenday.network.Track;
import com.example.greenday.viewmodel.ItunesViewModel;

import org.jetbrains.annotations.NotNull;

public class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {

    private final ObservableArrayList<Track> tracks;
    private final ItunesViewModel model;
    private int sizeBeforeUpdate;

    public TrackAdapter(ItunesViewModel model) {
        this.model=model;
        this.tracks = model.getTrackList();
    }

    @NotNull
    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTrackBinding binding = ItemTrackBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new TrackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrackViewHolder holder, int position) {
        holder.setData(tracks.get(position));
        if (position == getItemCount() - 10) model.loadTrackList(getItemCount(), 20);
    }

    public void updateTrack(){
        this.notifyItemRangeInserted(sizeBeforeUpdate, tracks.size() - sizeBeforeUpdate);
        sizeBeforeUpdate = tracks.size();
    }

    @Override
    public int getItemCount() { return tracks.size(); }
}