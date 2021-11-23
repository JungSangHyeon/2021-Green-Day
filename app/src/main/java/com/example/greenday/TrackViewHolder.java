package com.example.greenday;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.greenday.network.Results;

public class TrackViewHolder extends RecyclerView.ViewHolder {

    public TrackViewHolder(View itemView) {
        super(itemView);
        // initialize view...
    }

    public void setData(Results result) {
        TextView tv = this.itemView.findViewById(R.id.track_name);
        tv.setText(result.getTrackName());
    }
}
