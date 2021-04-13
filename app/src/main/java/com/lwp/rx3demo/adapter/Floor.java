package com.lwp.rx3demo.adapter;

import java.util.List;

public class Floor {

    private String floorNo;              // 楼层号
    private List<Room> roomList;        // 房间列表

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

}
