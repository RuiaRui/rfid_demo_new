package com.rfid.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.rfid.demo.entity.Product;
import com.rfid.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    ProductService productService;


    @RequestMapping(value = "all-product", method = RequestMethod.GET)
    public String getAllProduct( ) {
        List<Product> productList = productService.findAllProduct();
        return JSONObject.toJSONStringWithDateFormat(productList,"yyyy-MM-dd HH:mm:ss");
    }


    @RequestMapping(value = "add-product", method = RequestMethod.POST)
    public int addProduct(@RequestBody Product product) {
        return productService.add(product);
    }

    @RequestMapping(value = "update-product", method = RequestMethod.PUT)
    public int updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @RequestMapping(value = "delete-product", method = RequestMethod.POST)
    public int deleteProduct(@RequestBody String orderNum) {
        return productService.delete(orderNum);
    }




}
