package com.rfid.demo.entity;

import lombok.Data;

@Data
public class SonProduct {

    private String id;
    private String epc;
    private Product parent;
}
