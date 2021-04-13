package com.lwp.rx3demo.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarginDecoration extends RecyclerView.ItemDecoration {

    private int marginLeft = 1;
    private int marginRight = 1;
    private int marginTop = 16;
    private int marginBottom = 16;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.set(1, marginTop, 1, marginBottom);
    }
}
