package com.rfid.demo.entity;

public class Tag {
    /**
     * 标签ID
     */
    public String id;

    /**
     * 标签在所有时隙的位置
     */
    public Location location;

    /**
     * 构造函数
     */
    public Tag(String  id, Location loc) {
        this.id = id;
        this.location = loc;
    }
}
