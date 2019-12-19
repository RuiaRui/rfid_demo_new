package com.rfid.demo.dao;

import com.rfid.demo.entity.Product;

import java.util.List;

/**
 *
 */
public interface ProductDao {
    int add(Product product);

    int update(Product product);

    int delete(int id);

    Product findProductByOrder(int order);

    List<Product> findAllProduct();

    List<Product> findAllProductByEPC();


    int addEPC(String EPC);

    int alterEPC(String oldEPC,String newEPC);

    int writeEPC(String EPC,String orderNum);
}
