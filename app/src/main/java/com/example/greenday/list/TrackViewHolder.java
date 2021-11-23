package com.example.greenday.list;

import androidx.recyclerview.widget.RecyclerView;

import com.example.greenday.databinding.ItemTrackBinding;
import com.example.greenday.network.Track;

public class TrackViewHolder extends RecyclerView.ViewHolder {

    private final ItemTrackBinding binding;

    public TrackViewHolder(ItemTrackBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public void setData(Track track) {
        binding.setTrack(track);
    }
}
