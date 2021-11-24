package com.example.greenday.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greenday.R;
import com.example.greenday.databinding.FragmentTrackListBinding;
import com.example.greenday.view.trackList.TrackListAdapter;
import com.example.greenday.viewmodel.TrackListViewModel;

public class TrackListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TrackListViewModel model = new ViewModelProvider(requireActivity()).get(TrackListViewModel.class);

        FragmentTrackListBinding binding = FragmentTrackListBinding.inflate(inflater, container, false);
        binding.setTrackList(model.getTrackList());
        binding.title.setText(R.string.main_menu_track_list);
        binding.trackList.setAdapter(new TrackListAdapter(model));
        return binding.getRoot();
    }
}