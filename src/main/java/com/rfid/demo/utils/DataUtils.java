package com.rfid.demo.utils;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public  class DataUtils {

    public int getTagNum(String content){
        String s = (String) JSON.parseObject(content).get("num");
        return Integer.parseInt(s);
    }

    public int getMaxSlot(String content){
        String s = (String) JSON.parseObject(content).get("slot");
        return Integer.parseInt(s);
    }

    public int getReaderNum(String content){
        String s = (String) JSON.parseObject(content).get("num");
        return Integer.parseInt(s);
    }

    public int getReaderRi(String content){
        String s = (String) JSON.parseObject(content).get("ri");
        return Integer.parseInt(s);
    }

    public List<Double> getReaderPosition(String content){
        String value = (String) JSON.parseObject(content).get("position");
        String[] valueArr = value.split(" ");
        List<Double> position = new ArrayList<>();
        for (String s : valueArr) {
            position.add(Double.parseDouble(s));
        }

        return position;

    }

     public List<Double> getTagPosition(int slot,String content){
         JSONObject jobj = JSON.parseObject(content).getJSONObject("position");
         String key="slot"+slot;
         String value = (String)jobj.get(key);
         String[] valueArr = value.split(" ");
         List<Double> position = new ArrayList<>();
         for (String s : valueArr) {
             position.add(Double.parseDouble(s));
         }

         return position;

     }

     public  String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
