package com.rfid.demo.dao;

import com.rfid.demo.entity.Product;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SonProductDao {

    int addSonProducts(Product product);

    List<Map<String, Object>> findSonProductByEPC();

    List<String> getAllSonProductId();

    List<Map<String, Object>> getAllEpc();

    int addEPC(String EPC);

    int alterEPC(String oldEPC,String newEPC);

    int writeEPC(String EPC,String orderNum);
}
