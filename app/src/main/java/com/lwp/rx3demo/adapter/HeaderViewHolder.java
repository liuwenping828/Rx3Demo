package com.lwp.rx3demo.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.rx3demo.R;

class HeaderViewHolder extends RecyclerView.ViewHolder {

    final TextView title;
    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
    }
}
