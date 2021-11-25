package com.example.greenday.view.trackList;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.greenday.R;
import com.example.greenday.iTunes.Track;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop;

public class TrackListAdapterModule {

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String url) {
        int radius = (int) imageView.getContext().getResources().getDimension(R.dimen.artwork_rounding_radius);
        Glide.with(imageView).load(url).centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius))).into(imageView);
    }

    @BindingAdapter("loadData")
    public static void loadData(RecyclerView recyclerView, ObservableArrayList<Track> tracks) {
        ((Adapter)recyclerView.getAdapter()).datasetChanged();
    }
}
