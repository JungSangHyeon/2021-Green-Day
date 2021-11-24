package com.example.greenday.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greenday.R;
import com.example.greenday.databinding.FragmentTrackListBinding;
import com.example.greenday.view.trackList.FavoriteAdapter;
import com.example.greenday.viewmodel.TrackListViewModel;

public class FavoriteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TrackListViewModel model = new ViewModelProvider(requireActivity()).get(TrackListViewModel.class);

        FragmentTrackListBinding binding = FragmentTrackListBinding.inflate(inflater, container, false);
        binding.setTrackList(model.getFavorites());
        binding.title.setText(R.string.main_menu_favorite);
        binding.trackList.setAdapter(new FavoriteAdapter(model));
        return binding.getRoot();
    }
}