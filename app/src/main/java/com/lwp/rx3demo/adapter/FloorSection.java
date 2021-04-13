package com.lwp.rx3demo.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.rx3demo.R;
import com.lwp.section.Section;
import com.lwp.section.SectionParameters;

import java.util.List;

public class FloorSection extends Section {

    public String getTitle() {
        return title;
    }

    private final String title;
    private final List<Room> list;
    private final ClickListener clickListener;

    public interface ClickListener {

        void onHeaderMoreButtonClicked(@NonNull final FloorSection section, int itemAdapterPosition);

        void onItemRootViewClicked(@NonNull final Room section, final int itemAdapterPosition);
    }

    public FloorSection(String title, List<Room> list, ClickListener clickListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_item)
                .headerResourceId(R.layout.section_header)
                .build());

        this.title = title;
        this.list = list;
        this.clickListener = clickListener;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;
        Room room = list.get(position);
        itemHolder.name.setText(room.getName());
        itemHolder.itemView.setOnClickListener(v -> {
            if (clickListener != null){
                clickListener.onItemRootViewClicked(room,itemHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.title.setText(title);
        headerViewHolder.itemView.setOnClickListener(v -> {
            if (clickListener != null){
                clickListener.onHeaderMoreButtonClicked(this,holder.getBindingAdapterPosition());
            }
        });
    }
}
