package com.example.greenday.view.trackList;

import androidx.recyclerview.widget.RecyclerView;

import com.example.greenday.databinding.ItemTrackBinding;
import com.example.greenday.iTunes.Track;
import com.example.greenday.viewmodel.TrackListViewModel;

public class TrackViewHolder extends RecyclerView.ViewHolder {

    private final ItemTrackBinding binding;
    private final TrackListViewModel model;

    public TrackViewHolder(ItemTrackBinding binding, TrackListViewModel model) {
        super(binding.getRoot());
        this.binding=binding;
        this.model=model;
    }

    public void setData(Track track) {
        binding.setTrack(track);
        binding.favorite.setOnCheckedChangeListener((compoundButton, b) -> {
            if(track.isFavorite()!=b) model.favoriteChange(track, b);
        });
    }
}
