package com.mitko.livedemonavigation.models;

import java.io.Serializable;

public  class  DrawerItemInfo implements Serializable{
    private int id;
    private  String title;

    public DrawerItemInfo(int id, String title) {
        this.setId(id);
        this.setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
