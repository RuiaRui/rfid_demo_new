package com.rfid.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private String orderNum;
    private String name;

    private int inventory;
    private int expiration;

    private Date productionDate;
    private String specification;
    private String QRUrl;


}
