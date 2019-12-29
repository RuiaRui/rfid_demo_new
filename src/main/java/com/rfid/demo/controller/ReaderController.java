package com.rfid.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rfid.demo.entity.Location;
import com.rfid.demo.entity.Reader;
import com.rfid.demo.entity.SonProduct;
import com.rfid.demo.entity.Tag;
import com.rfid.demo.service.ProductService;
import com.rfid.demo.service.ReaderService;
import com.rfid.demo.service.SonProductService;
import com.rfid.demo.utils.Method;
import com.rfid.demo.utils.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @Autowired
    SonProductService sonProductService;



    @RequestMapping(value = "tag-status", method = RequestMethod.GET)
    public String getAllTagStatus( ) {
        Method.setTagParameter(Parameter.currentslot);
        List<String> ids=sonProductService.getAllEpc();
        List<String> boundedIds=sonProductService.getAllBoundEpc();

        List<Tag> tagList= Method.Position2TagList(ids);
        List<Reader> readerList=Method.getReaderList();

        JSONArray result1 = new JSONArray();
        int existN=0;
        for(int i=0;i<Parameter.tagNum;i++) {
            Tag t=tagList.get(i);
            JSONObject jo = new JSONObject();
            jo.put("epc", t.getId());

            if((boundedIds.contains(t.getId()))){
                jo.put("status", 1);

            }else{
                jo.put("status", 0);
            }
            if (readerService.checkTagForAll(readerList, t)) {
                jo.put("exist", 1);
                existN++;
            } else {
                jo.put("exist", 0);
            }
            result1.add(jo);
        }

        JSONObject number = new JSONObject();
        number.put("number", Parameter.tagNum);
        number.put("exist",existN);
        number.put("out",Parameter.tagNum-existN);

        JSONObject result = new JSONObject();
        result.put("tag", result1);
        result.put("slot",Parameter.currentslot+1);
        result.put("number",number);

        Method.nextSlot();

        return result.toJSONString();
    }

    @RequestMapping(value = "tag-position", method = RequestMethod.GET)
    public String getAllTagPosition( ) {
        Method.setTagParameter(Parameter.currentslot);
        List<String> ids=sonProductService.getAllEpc();

        List<Tag> tagList= Method.Position2TagList(ids);
        List<Reader> readerList=Method.getReaderList();

        JSONArray result = new JSONArray();
        for(int i=0;i<Parameter.tagNum;i++) {
            Tag t=tagList.get(i);
            JSONObject jo = new JSONObject();
            jo.put("epc", t.getId());
            jo.put("loc",t.getLocation());
            int num=0;
            String s="";
            String temp="";
            for(int j=0;j<Parameter.readerNum;j++){
                if(readerService.checkTagForOne(readerList.get(j),t)){
                    temp=temp+readerList.get(j).getId()+" ";
                    num++;
                }
            }
            if(num==0){
                s="不在读写器范围内";
            }else if(num==1){
                s="在读写器"+temp+"的范围内";
            }else {
                s="在读写器"+temp+"的交界范围内，请注意信号干扰";
            }

            jo.put("status",s);
            result.add(jo);
        }

        JSONObject out = new JSONObject();
        out.put("tags", result);
        out.put("slot",Parameter.currentslot+1);
        out.put("readers",Parameter.readerList);

        Method.nextSlot();
        return out.toJSONString();
    }

    @RequestMapping(value = "add-reader", method = RequestMethod.POST)
    public int addReaders(@RequestBody String loc){
        JSONObject jsonObject1 =  JSONObject.parseObject(loc);
        Location l=new Location(Double.parseDouble(jsonObject1.get("x").toString()),Double.parseDouble(jsonObject1.get("y").toString()));
        readerService.addReader(l);
        return 1;
    }

    @RequestMapping(value = "edit-reader", method = RequestMethod.POST)
    public int editReaders(@RequestBody String reader){
        JSONObject jsonObject=JSONObject.parseObject(reader);
        JSONObject jsonObject1=JSONObject.parseObject(jsonObject.get("loc").toString());
        Location loc=new Location(Double.parseDouble(jsonObject1.get("x").toString()),Double.parseDouble(jsonObject1.get("y").toString()));

        Reader r=new Reader((int)jsonObject.get("id"),loc);
        readerService.editReader(r);
        return 1;
    }

    @RequestMapping(value = "delete-reader", method = RequestMethod.POST)
    public int deleteReaders(@RequestBody String id){
        readerService.deleteReader(Integer.parseInt(id));
        return 1;
    }


}
