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

public class FavoriteAdapter extends RecyclerView.Adapter<TrackViewHolder> implements Adapter{

    private final ObservableArrayList<Track> tracks;
    private final TrackListViewModel model;

    public FavoriteAdapter(TrackListViewModel model) {
        this.model=model;
        this.tracks = model.getFavorites();
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
    }

    @Override
    public void datasetChanged() { this.notifyDataSetChanged(); }

    @Override
    public int getItemCount() { return tracks.size(); }
}