package com.example.a16022667.taskmanager;

import java.io.Serializable;

public class Task implements Serializable{
    private int id;
    private String name;
    private String desc;

    public Task(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
