package com.rfid.demo.service;

import com.rfid.demo.entity.Product;

import java.util.List;

public interface ProductService {

    int add(Product product);


    int update(Product product);

    int delete(String orderNum);

    Product findProductByOrder(String orderNum);

    List<Product> findAllProduct();
}
