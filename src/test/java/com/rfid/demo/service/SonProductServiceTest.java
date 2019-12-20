package com.rfid.demo.service;

import com.rfid.demo.entity.SonProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SonProductServiceTest {

    @Autowired
    private SonProductService sonProductService;

    @Test
    void addSonProducts() {
    }

    @Test
    void findSonProductByEPC() {
        List<SonProduct> productList=sonProductService.findSonProductByEPC();
        System.out.println(productList);
    }

    @Test
    void getAllSonProductId() {
    }

    @Test
    void addEPC() {
    }

    @Test
    void alterEPC() {
    }

    @Test
    void writeEPC() {
    }
}