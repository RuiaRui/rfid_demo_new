package com.rfid.demo.entity;

public class Reader {
    /**
     * 读写器位置，在所有时隙读写器位置不变
     */
    public Location loc;

    /**
     * 读写器ID，每个读写器的ID是唯一的
     */
    public int id;

    /**
     * 构造函数
     * @param id ID
     * @param loc 位置
     */
    public Reader(int id, Location loc) {
        this.id = id;
        this.loc = loc;
    }

}


