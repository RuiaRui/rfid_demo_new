package com.rfid.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.rfid.demo.entity.Product;
import com.rfid.demo.entity.SonProduct;
import com.rfid.demo.service.ProductService;
import com.rfid.demo.service.SonProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SonProductContorller {
    @Autowired
    SonProductService sonProductService;

    @RequestMapping(value = "all-epc-product", method = RequestMethod.GET)
    public String getAllProduct( ) {
        List<SonProduct> productList = sonProductService.findSonProductByEPC();
        return JSONObject.toJSONStringWithDateFormat(productList,"yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping(value = "all-son-id", method = RequestMethod.GET)
    public String getSonProductId( ) {
        List<String> sonProductIds = sonProductService.getAllSonProductId();
        return JSONObject.toJSONString(sonProductIds);
    }

    @RequestMapping(value = "write-epc", method = RequestMethod.POST)
    public int updateEpc(@RequestBody String data) {
        JSONObject jsonObject=JSONObject.parseObject(data);
        return sonProductService.writeEPC(jsonObject.getString("epc"),jsonObject.getString("id"));
    }

    @RequestMapping(value = "add-epc", method = RequestMethod.POST)
    public int addEpc(@RequestBody String epc) {
        JSONObject jsonObject=JSONObject.parseObject(epc);
        return sonProductService.addEPC(jsonObject.getString("epc"));
    }
}
