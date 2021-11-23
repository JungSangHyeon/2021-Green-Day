package com.example.greenday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenday.network.Response;

import org.jetbrains.annotations.NotNull;

public class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {

    Response response;

    public TrackAdapter(Response response){
        this.response=response;
    }

    @NotNull
    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, parent,false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrackViewHolder holder, int position) {
        holder.setData(response.getResults()[position]);
    }

    @Override
    public int getItemCount() {
        return response.getResultCount();
    }
}