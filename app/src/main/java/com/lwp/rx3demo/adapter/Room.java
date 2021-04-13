package com.lwp.rx3demo.adapter;

public class Room {

    final String name;
    String info;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
