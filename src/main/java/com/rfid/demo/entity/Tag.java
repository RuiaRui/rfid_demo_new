package com.rfid.demo.entity;

import lombok.Data;

@Data
public class Tag {
    /**
     * 标签ID
     */
    private String id;

    /**
     * 标签在所有时隙的位置
     */
    private Location location;

    /**
     * 构造函数
     */
    public Tag(String  id, Location loc) {
        this.id = id;
        this.location = loc;
    }
}
