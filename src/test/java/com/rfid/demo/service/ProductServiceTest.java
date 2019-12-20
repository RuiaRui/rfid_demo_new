package com.rfid.demo.service;

import com.rfid.demo.entity.Product;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void addTest() {
        Product product=new Product();
        product.setOrderNum("SC000001001");
        product.setInventory(4);
        product.setName("testP3");
        product.setProductionDate(new Date(System.currentTimeMillis()));
        product.setExpiration(20);
        product.setSpecification("20l/kg");
        product.setQRUrl("http://1234test.com");

        int[] i=productService.add(product);
        assertEquals(4,i[1]);
    }

    @Test
    void updateTest() {
        Product product=new Product();
        product.setOrderNum("SC000001001");
        product.setInventory(4);
        product.setName("testP3Alter");
        product.setProductionDate(new Date(System.currentTimeMillis()));
        product.setExpiration(20);
        product.setSpecification("20l/kg");
        product.setQRUrl("http://1234test.com");

        int i=productService.update(product);
        assertEquals(1,i);
    }

    @Test
    void deleteTest() {
        Product product=new Product();
        product.setOrderNum("SC20192085001");
        product.setInventory(5);
        product.setName("testA1");
        product.setProductionDate(new Date(System.currentTimeMillis()));
        product.setExpiration(20);
        product.setSpecification("20l/kg");
        product.setQRUrl("http://1234test.com");

        productService.add(product);

        int i=productService.delete("SC20192085001");

        assertEquals(1,i);
    }

    @Test
    void findProductByOrderTest() {
        String order="SC000001001";
        Product product=productService.findProductByOrder(order);

        assertEquals("testP3Alter",product.getName());
    }

    @Test
    void findAllProductTest() {
        List<Product> products=new ArrayList<>();
        products=productService.findAllProduct();
    }
}