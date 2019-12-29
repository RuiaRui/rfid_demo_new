package com.rfid.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@JsonFormat()
public class Location {
    private double x;
    private double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
