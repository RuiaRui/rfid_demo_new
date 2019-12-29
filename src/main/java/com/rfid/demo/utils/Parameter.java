package com.rfid.demo.utils;


import com.rfid.demo.controller.ReaderController;
import com.rfid.demo.entity.Reader;

import java.util.List;

public class Parameter {
    /**
     * 读写器部署区域的最大值
     */
    public static int maxPosition = 10;

    /**
     * 读写器部署区域的最小值
     */
    public static int minPosition = 0;

    /**
     * 标签数
     */
    public static int tagNum;

    /**
     * 读写器数量
     */
    public static int readerNum ;

    public static double ri ;

    public static List<Double>  readerPositionList;

    public static List<Reader>  readerList;

    public static List<Double> tagPositionList;

    public static int currentslot=0;

    public static int maxSlot;


}
