package com.rfid.demo.service;

import com.rfid.demo.entity.Product;
import com.rfid.demo.entity.SonProduct;

import java.util.List;

public interface SonProductService {
    int addSonProducts(Product product);

    List<SonProduct> findSonProductByEPC();

    List<String> getAllSonProductId();


    int addEPC(String EPC);

    int alterEPC(String oldEPC,String newEPC);

    int writeEPC(String EPC,String orderNum);

}
