package com.example.greenday;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greenday.databinding.FragmentTrackListBinding;
import com.example.greenday.list.TrackAdapter;
import com.example.greenday.viewmodel.ItunesViewModel;

public class TrackListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ItunesViewModel model = new ViewModelProvider(this).get(ItunesViewModel.class);
        model.get();

        FragmentTrackListBinding binding = FragmentTrackListBinding.inflate(inflater, container, false);
        binding.setTrackList(model.getTracks());
        binding.title.setText(R.string.main_menu_track_list);
        binding.trackList.setAdapter(new TrackAdapter(model));

        return binding.getRoot();
    }
}