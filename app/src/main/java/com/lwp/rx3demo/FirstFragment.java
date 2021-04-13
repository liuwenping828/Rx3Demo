package com.lwp.rx3demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lwp.rx3demo.adapter.Floor;
import com.lwp.rx3demo.adapter.FloorSection;
import com.lwp.rx3demo.adapter.MarginDecoration;
import com.lwp.rx3demo.adapter.Room;
import com.lwp.section.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements FloorSection.ClickListener {

    private RecyclerView recyclerView;
    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);
        sectionedAdapter = new SectionedRecyclerViewAdapter();

        initData();

        final GridLayoutManager glm = new GridLayoutManager(getContext(), 4);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if (sectionedAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 4;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(glm);
        recyclerView.addItemDecoration(new MarginDecoration());
        recyclerView.setAdapter(sectionedAdapter);

    }

    private void initData() {
        List<Floor> floors = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            Floor floor = new Floor();
            floor.setFloorNo(i + "层楼");
            List<Room> roomList = new ArrayList<Room>();
            for (int j = 1; j <= i; j++) {
                Room room = new Room(i * 100 + "" + j);
                roomList.add(room);
            }
            floor.setRoomList(roomList);
            floors.add(floor);
        }

        for (Floor floor : floors) {
            sectionedAdapter.addSection(new FloorSection(floor.getFloorNo(),
                    floor.getRoomList(), this));
        }

    }


    @Override
    public void onHeaderMoreButtonClicked(@NonNull FloorSection section, int itemAdapterPosition) {
        Toast.makeText(getContext(), section.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemRootViewClicked(@NonNull Room section, int itemAdapterPosition) {
        Toast.makeText(getContext(), section.getName(), Toast.LENGTH_LONG).show();
    }
}