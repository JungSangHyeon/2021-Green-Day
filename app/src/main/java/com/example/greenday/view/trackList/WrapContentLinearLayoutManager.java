package com.example.greenday.view.trackList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 가끔 발생하는 에러 처리에 사용.
 * 참고: https://stackoverflow.com/questions/31759171/recyclerview-and-java-lang-indexoutofboundsexception-inconsistency-detected-in
 */
public class WrapContentLinearLayoutManager extends LinearLayoutManager {

    public WrapContentLinearLayoutManager(Context context) { super(context); }
    public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) { super(context, orientation, reverseLayout); }
    public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try { super.onLayoutChildren(recycler, state); }
        catch (IndexOutOfBoundsException e) { Log.e("TAG", "meet a IOOBE in RecyclerView"); }
    }
}