package com.example.greenday.view.trackList;

import android.animation.Animator;
import android.animation.AnimatorInflater;

import androidx.recyclerview.widget.RecyclerView;

import com.example.greenday.R;
import com.example.greenday.databinding.ItemTrackBinding;
import com.example.greenday.iTunes.Track;
import com.example.greenday.viewmodel.TrackListViewModel;

public class TrackViewHolder extends RecyclerView.ViewHolder {

    private final ItemTrackBinding binding;
    private final TrackListViewModel model;
    private final Animator animator;

    public TrackViewHolder(ItemTrackBinding binding, TrackListViewModel model) {
        super(binding.getRoot());
        this.binding=binding;
        this.model=model;

        animator = AnimatorInflater.loadAnimator(itemView.getContext(), R.animator.favorite_animation);
        animator.setTarget(binding.favorite);
    }

    public void setData(Track track) {
        binding.setTrack(track);
        binding.favorite.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) animator.start(); else animator.end();
            if(track.isFavorite()!=b) model.favoriteChange(track, b);
        });
    }
}
