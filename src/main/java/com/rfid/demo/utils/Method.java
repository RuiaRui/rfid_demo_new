package com.rfid.demo.utils;

import com.rfid.demo.entity.Location;
import com.rfid.demo.entity.Reader;
import com.rfid.demo.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Method {

    Parameter parameter=new Parameter();



    /**
     * 确定某个位置是否位于读写器的范围内
     *
     * @param loc_x    位置的X坐标
     * @param loc_y    位置的Y坐标
     * @param reader_x 读写器的X坐标
     * @param reader_y 读写器的Y坐标
     * @param r        读写器半径
     * @return true false
     */
    public static boolean locWithinReader(double loc_x, double loc_y,
                                          double reader_x, double reader_y,
                                          double r) {
        return Math.pow(loc_x - reader_x, 2) + Math.pow(loc_y - reader_y, 2) < r * r;
    }
    /**
     * 将double数组的位置转化为读写器集合
     * @return
     */


    public static List<Reader> getReaderList() {
        if(Parameter.readerList==null){
            getReaderParameter();
            List<Double> position=Parameter.readerPositionList;
            List<Reader> readers=new ArrayList<>();
            for (int i = 0; i < Parameter.readerNum; i++) {
                Reader r = new Reader(i+1, new Location(position.get(2 * i), position.get(2 * i + 1)));
                readers.add(r);
            }
            Parameter.readerList=readers;
        }
        return Parameter.readerList;
    }

    public static List<Tag> Position2TagList(List<String> id) {
        List<Tag> tagList = new ArrayList<>();
        List<Double> position=Parameter.tagPositionList;
        for (int i = 0; i < Parameter.tagNum; i++) {
            Tag t = new Tag(id.get(i), new Location(position.get(2 * i), position.get(2 * i + 1)));
            tagList.add(t);
        }

        return tagList;
    }

    private static int random(){
        int i=new Random().nextInt(Parameter.maxPosition+1);
        return i;
    }


    public static double[] GerenateTagPosition(int tagNum){
         double[] tagPosition= new double[tagNum*2];
         for(int i=0;i<tagNum;i++){
             tagPosition[2*i]=random();
             tagPosition[2*i+1]=random();
         }

         return tagPosition;
    }

    public static void setTagParameter(int slot){
        String path = Parameter.class.getClassLoader().getResource("tags.json").getPath();
        DataUtils dataUtils=new DataUtils();
        String content=dataUtils.readJsonFile(path);
        Parameter.tagNum=dataUtils.getTagNum(content);
        Parameter.tagPositionList=dataUtils.getTagPosition(slot+1,content);
        Parameter.maxSlot=dataUtils.getMaxSlot(content);
    }

    public static void getReaderParameter(){
        if(Parameter.readerPositionList!=null){
            return;
        }else {
            String path = Parameter.class.getClassLoader().getResource("readers.json").getPath();
            DataUtils dataUtils=new DataUtils();
            String content=dataUtils.readJsonFile(path);
            Parameter.readerNum=dataUtils.getReaderNum(content);
            Parameter.ri=dataUtils.getReaderRi(content);
            Parameter.readerPositionList=dataUtils.getReaderPosition(content);
        }

    }

    public static void nextSlot(){
        Parameter.currentslot=(Parameter.currentslot+1)%Parameter.maxSlot;
    }
}
